#!/usr/bin/env bash
set -euo pipefail
# Helper script to boot the local development stack for Hexabank
# Usage: ./run-local-dev.sh

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$ROOT_DIR"

echo "== Hexabank local dev bootstrap =="

# Requirements
command -v docker >/dev/null 2>&1 || { echo "docker is required but not found" >&2; exit 1; }
command -v docker-compose >/dev/null 2>&1 || { echo "docker-compose is required but not found" >&2; exit 1; }

# Copy .env.example if .env doesn't exist
if [ ! -f .env ] && [ -f .env.example ]; then
  echo "Creating .env from .env.example"
  cp .env.example .env
fi

echo "Building ms-client and ms-account jars (skip tests)"
if [ -x ./ms-client/gradlew ]; then
  ./ms-client/gradlew :ms-client:bootJar -x test
else
  (cd ms-client && gradle :ms-client:bootJar -x test)
fi

if [ -x ./ms-account/gradlew ]; then
  ./ms-account/gradlew :ms-account:bootJar -x test
else
  (cd ms-account && gradle :ms-account:bootJar -x test)
fi

echo "Pulling Docker images"
docker-compose pull

echo "Starting core infra: postgres, zookeeper, kafka"
docker-compose up -d postgres zookeeper kafka

echo "Running kafka-init (idempotent topic creator)"
docker-compose up --no-deps --force-recreate kafka-init || true

# Wait for Kafka to be ready by attempting to list topics
echo "Waiting for Kafka to accept connections (up to 120s)"
for i in $(seq 1 120); do
  if docker-compose exec kafka /usr/bin/kafka-topics --bootstrap-server kafka:9092 --list > /dev/null 2>&1; then
    echo "Kafka is ready"
    break
  fi
  sleep 1
done

echo "Ensuring required topics exist (account-created, movement-created)"
docker-compose exec kafka /usr/bin/kafka-topics --bootstrap-server kafka:9092 --create --topic account-created --partitions 1 --replication-factor 1 || true
docker-compose exec kafka /usr/bin/kafka-topics --bootstrap-server kafka:9092 --create --topic movement-created --partitions 1 --replication-factor 1 || true

echo "Building and starting applications: ms-client, ms-account"
docker-compose up -d --build ms-client ms-account

echo "Done. Check health endpoints:"
echo "  ms-client: http://localhost:8081/actuator/health"
echo "  ms-account: http://localhost:8082/actuator/health"
echo "Check topics: docker-compose exec kafka /usr/bin/kafka-topics --bootstrap-server kafka:9092 --list"

exit 0
