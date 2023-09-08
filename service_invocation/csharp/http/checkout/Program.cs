using Dapr.Client;

using var client = new DaprClientBuilder().Build();

for (int i = 1; i <= 20; i++)
{
    var data = new { orderId = i };

    var cts = new CancellationTokenSource();
    Console.CancelKeyPress += (object? sender, ConsoleCancelEventArgs e) => cts.Cancel();
    // Invoking a service
    await client.InvokeMethodAsync<object>(
        "order-processor",
        "orders",
        data,
        cts.Token
    );
    Console.WriteLine("Order passed: " + i);

    await Task.Delay(TimeSpan.FromSeconds(1));
}
