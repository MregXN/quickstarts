import json
import time
import logging
from dapr.clients import DaprClient

logging.basicConfig(level=logging.INFO)


with DaprClient() as d:
    for i in range(1, 20):
        order = {"orderId": i}

        resp = d.invoke_method(
            app_id='order-processor',
            method_name='orders',
            data=json.dumps(order),
            http_verb='POST',
        )

        print("Order passed: " + json.dumps(order), flush=True)

        time.sleep(1)
