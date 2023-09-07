package main

import (
	"context"
	"fmt"
	"strconv"

	dapr "github.com/dapr/go-sdk/client"
)

func main() {
	client, err := dapr.NewClient()
	if err != nil {
		panic(err)
	}
	defer client.Close()

	for i := 1; i <= 20; i++ {
		order := `{"orderId":` + strconv.Itoa(i) + "}"
		content := &dapr.DataContent{
			ContentType: "text/plain",
			Data:        []byte(order),
		}
		ctx := context.Background()
		_, err := client.InvokeMethodWithContent(ctx, "order-processor", "orders", "post", content)
		if err != nil {
			panic(err)
		}

		fmt.Println("Order passed:" + strconv.Itoa(i))
	}
}
