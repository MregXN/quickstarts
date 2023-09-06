package com.service;

import org.json.JSONObject;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.HttpExtension;

public class CheckoutServiceApplication {

	private static final String SERVICE_APP_ID = "order-processor";

	public static void main(String[] args) throws Exception {
		try (DaprClient client = (new DaprClientBuilder()).build()) {
			for (int i = 1; i <= 20; i++) {
				int orderId = i;
				JSONObject obj = new JSONObject();
				obj.put("orderId", orderId);

				byte[] response = client.invokeMethod(SERVICE_APP_ID, "orders", obj.toString().getBytes("UTF-8"),
						HttpExtension.POST, null,
						byte[].class).block();
				System.out.println("Order passed: " + orderId);
				TimeUnit.MILLISECONDS.sleep(1000);
			}
		}
	}
}
