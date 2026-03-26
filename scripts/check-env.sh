#!/usr/bin/env bash
set -euo pipefail

# Check required environment variables for running the stack locally.
required=(
  SPRING_DATASOURCE_URL
  SPRING_DATASOURCE_USERNAME
  SPRING_DATASOURCE_PASSWORD
  SPRING_KAFKA_BOOTSTRAP_SERVERS
)
missing=()
for v in "${required[@]}"; do
  if [ -z "${!v:-}" ]; then
    missing+=("$v")
  fi
done
if [ ${#missing[@]} -ne 0 ]; then
  echo "Missing required environment variables: ${missing[*]}"
  echo "Create a .env from .env.example and export it, or set the variables in your shell." >&2
  exit 1
fi
echo "Environment variables check: OK"
