import { DaprClient, HttpMethod } from "@dapr/dapr";

const DAPR_HOST = process.env.DAPR_HOST || "http://localhost";

async function main() {

  const client = new DaprClient({ DAPR_HOST });

  for (var i = 1; i <= 20; i++) {
    const order = { orderId: i };


    const r = await client.invoker.invoke(
      "order-processor",
      "orders",
      HttpMethod.POST,
      order,
    );

    console.log("Order passed: " + i);

    await sleep(1000);
  }
}

async function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

main().catch(e => console.error(e))