import os
import json
import time
import re
from google.cloud import pubsub_v1

PROJECT_ID = os.environ["GOOGLE_CLOUD_PROJECT"]
TOPIC_SUFFIX = os.environ["TOPIC_SUFFIX"]

SUBSCRIBER_ID = "0"
RULES_FILE = "rules.json"

subscriber = pubsub_v1.SubscriberClient()

rules = {}
last_loaded = 0

def load_rules():
    global rules, last_loaded

    with open(RULES_FILE) as f:
        data = json.load(f)

    rules = {}
    for rule in data["subscribers"].get(SUBSCRIBER_ID, []):
        level = rule["level"]
        pattern = rule["pattern"]

        if level not in rules:
            rules[level] = []

        rules[level].append(re.compile(pattern))

    last_loaded = time.time()
    print(f"[Subscriber {SUBSCRIBER_ID}] Rules reloaded")

def callback(level):
    def handle(message):
        global last_loaded

        if time.time() - last_loaded > 10:
            load_rules()

        text = message.data.decode("utf-8")

        for pattern in rules.get(level, []):
            if pattern.search(text):
                print(f"[Subscriber {SUBSCRIBER_ID} MATCH] {level}: {text}")
                break

        message.ack()

    return handle

load_rules()

subscriptions = []

for level in rules.keys():
    topic = f"{level}-{TOPIC_SUFFIX}"
    sub_name = f"sub-{level}-{TOPIC_SUFFIX}-{SUBSCRIBER_ID}"

    topic_path = subscriber.topic_path(PROJECT_ID, topic)
    sub_path = subscriber.subscription_path(PROJECT_ID, sub_name)

    try:
        subscriber.create_subscription(name=sub_path, topic=topic_path)
    except Exception:
        pass

    subscriber.subscribe(sub_path, callback(level))
    print(f"Subscribed to {topic}")

print(f"Subscriber {SUBSCRIBER_ID} running...")

while True:
    time.sleep(1)
