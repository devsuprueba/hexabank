#!/usr/bin/env bash
set -euo pipefail

# Check that required Kafka topics exist in the docker-compose kafka container.
# Usage: ./scripts/check-topics.sh account-created movement-created

if [ "$#" -lt 1 ]; then
  echo "Usage: $0 topic1 [topic2 ...]" >&2
  exit 2
fi

TOPICS=("$@")

echo "Listing topics in kafka..."
topics=$(docker-compose exec -T kafka /opt/bitnami/kafka/bin/kafka-topics.sh --list --bootstrap-server kafka:9092 2>/dev/null || true)

if [ -z "$topics" ]; then
  echo "Could not list topics. Ensure kafka service is up and reachable via docker-compose." >&2
  exit 3
fi

missing=()
for t in "${TOPICS[@]}"; do
  if ! echo "$topics" | grep -qx "$t"; then
    missing+=("$t")
  fi
done

if [ ${#missing[@]} -ne 0 ]; then
  echo "Missing topics: ${missing[*]}" >&2
  echo "Run: docker-compose up --no-deps --force-recreate kafka-init" to create them (if configured)." >&2
  exit 4
fi

echo "All topics present: ${TOPICS[*]}"
