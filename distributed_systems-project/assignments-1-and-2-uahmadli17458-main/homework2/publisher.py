import csv
import time
import os
from google.cloud import pubsub_v1

PROJECT_ID = os.environ["GOOGLE_CLOUD_PROJECT"]
TOPIC_SUFFIX = os.environ["TOPIC_SUFFIX"]

publisher = pubsub_v1.PublisherClient()

print("Publisher started...")

while True:
    with open("logs.csv", newline="", encoding="utf-8") as f:
        reader = csv.DictReader(f)

        for row in reader:
            level = row["level"].strip()
            message = row["message"].strip()

            topic_name = f"{level}-{TOPIC_SUFFIX}"
            topic_path = publisher.topic_path(PROJECT_ID, topic_name)

            publisher.publish(topic_path, message.encode("utf-8"))

            print(f"[PUBLISHED] {level}: {message}")
            time.sleep(2)
