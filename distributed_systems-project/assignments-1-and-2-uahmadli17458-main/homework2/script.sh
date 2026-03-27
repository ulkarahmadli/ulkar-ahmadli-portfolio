#!/bin/bash

ACTION=$1
PROJECT_ID=$(gcloud config get-value project)
TOPIC_SUFFIX=$TOPIC_SUFFIX

LEVELS=("INFO" "WARN" "ERROR" "DEBUG" "ALERT")
SUBSCRIBERS=("0" "1" "2" "3")

if [ -z "$TOPIC_SUFFIX" ]; then
  echo "Error: TOPIC_SUFFIX is not set"
  echo "Run: export TOPIC_SUFFIX=your_suffix"
  exit 1
fi

setup() {
  echo "Starting setup for suffix: $TOPIC_SUFFIX"

  echo "Creating topics..."
  for level in "${LEVELS[@]}"; do
    gcloud pubsub topics create "${level}-${TOPIC_SUFFIX}" \
      --project="$PROJECT_ID" 2>/dev/null
  done

  echo "Creating subscriptions..."
  for sub_id in "${SUBSCRIBERS[@]}"; do
    for level in "${LEVELS[@]}"; do
      gcloud pubsub subscriptions create "sub-${level}-${TOPIC_SUFFIX}-${sub_id}" \
        --topic "${level}-${TOPIC_SUFFIX}" \
        --project="$PROJECT_ID" 2>/dev/null
    done
  done

  echo "Setup completed"
}

teardown() {
  echo "Starting teardown for suffix: $TOPIC_SUFFIX"

  echo "Deleting subscriptions..."
  for sub_id in "${SUBSCRIBERS[@]}"; do
    for level in "${LEVELS[@]}"; do
      gcloud pubsub subscriptions delete "sub-${level}-${TOPIC_SUFFIX}-${sub_id}" \
        --project="$PROJECT_ID" 2>/dev/null
    done
  done

  echo "Deleting topics..."
  for level in "${LEVELS[@]}"; do
    gcloud pubsub topics delete "${level}-${TOPIC_SUFFIX}" \
      --project="$PROJECT_ID" 2>/dev/null
  done

  echo "Teardown completed"
}

case "$ACTION" in
  setup)
    setup
    ;;
  teardown)
    teardown
    ;;
  *)
    echo "Usage: ./script.sh setup | teardown"
    ;;
esac
