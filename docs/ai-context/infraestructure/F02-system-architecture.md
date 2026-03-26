# Feature: System Architecture

## Overview

The system is composed of:

- ms-clients
- ms-accounts

## Architecture Style

- Microservices
- Hexagonal architecture
- Event-driven communication

## Communication

- Kafka is used for asynchronous communication

## Data Flow

1. Client created in ms-clients
2. Event published to Kafka
3. ms-accounts consumes event

## Infrastructure

- Docker Compose for local orchestration
- Kubernetes for deployment

## Principles

- Decoupled services
- Scalability
- Maintainability

## Details and operational notes

- Topics used by the system (must exist before producers publish or be created idempotently at startup):
  - `account-created`
  - `movement-created`

- Recommended ports and endpoints:
  - `ms-client` HTTP: 8081 (container exposes 8080, mapped to 8081 in docker-compose)
  - `ms-account` HTTP: 8082 (container exposes 8080, mapped to 8082 in docker-compose)
  - Kafka: 9092
  - Zookeeper: 2181
  - Postgres: 5432
  - Actuator endpoints: `/actuator/health`, `/actuator/prometheus`

## Supported versions (recommended)

- Java: 17+ (project compiled for Java 21 in codebase; ensure runtime compatibility)
- Postgres: 13+
- Kafka: 2.8+ (bitnami/kafka:3 image is acceptable)

## Repository references

- Docker Compose (root): `docker-compose.yml`
- ms-client Dockerfile: `ms-client/Dockerfile` (or `ms-client/build.gradle` build configuration)
- ms-account Dockerfile: `ms-account/Dockerfile`
- Flyway migrations per service: `src/main/resources/db/migration/` inside each service
- Postman collections: `ms-client/docs/postman/` and `ms-account/docs/postman/`

## Topic creation strategy

1. Prefer creating topics idempotently using an init container or a small helper container that runs `kafka-topics.sh --create --if-not-exists` for the required topics. This avoids race conditions and manual intervention.
2. Alternatively, configure the Kafka image env (if supported) to create topics automatically (some images accept a `KAFKA_CREATE_TOPICS`-style env var).
3. Ensure the topic-creation step retries until Kafka is ready (broker accepts connections), and that commands ignore "already exists" errors.

## Deployment patterns

- Local (developer): `docker-compose up --build` to start Postgres, Zookeeper, Kafka and services. Use a `kafka-init` helper service to create topics.
- Kubernetes: provide `k8s/` manifests (Deployment, Service, Secret) per service. Use an initContainer to create topics or a Kubernetes Job that runs once.

## Observability

- Expose Actuator endpoints and enable Prometheus scraping using annotations on pods in Kubernetes (e.g. `prometheus.io/scrape: "true"`).
- Include MDC values in logs (requestId) and ensure logs are structured enough for scraping.

## Migrations and seed data

- Flyway migrations must own schema creation. Do not commit a pre-built schema outside migrations unless an explicit `BaseDatos.sql` is required for delivery — in that case add it to `/docs` by concatenating migrations.
- Seed data can be provided via Flyway `V*_seed.sql` migrations.

## Acceptance criteria

1. `docker-compose up` brings up `postgres`, `zookeeper`, `kafka`, `ms-client` and `ms-account` and they become healthy.
2. The helper/init service creates (idempotently) the topics `account-created` and `movement-created`.
3. Flyway migrations are applied successfully and seed data (if present) is inserted.
4. `/actuator/health` returns 200 for both services and `/actuator/prometheus` exposes metrics.
5. A basic E2E smoke test runs: create client via `ms-client` → verify an `account-created` event is published → verify `ms-account` consumes event (can be validated via logs or a test consumer).

## How to validate locally (quick checklist)

1. Start infra (detached):

```bash
docker-compose up -d zookeeper kafka postgres
```

2. Run topic initializer (idempotent helper):

```bash
docker-compose up --no-deps --force-recreate kafka-init
docker-compose logs kafka-init
```

3. Verify topics exist:

```bash
docker-compose exec kafka /opt/bitnami/kafka/bin/kafka-topics.sh --list --bootstrap-server kafka:9092
# expect: account-created, movement-created
```

4. Start services:

```bash
docker-compose up --build ms-client ms-account
```

5. Run smoke test (example): create client using Postman or curl and check ms-account processed event.

## Notes and recommendations

- For CI, prefer Testcontainers for E2E tests that require Postgres/Kafka instead of bringing docker-compose up on runners.
- Do not commit secrets. Use `.env` files or CI secrets and Kubernetes Secrets for production.
- Keep topic creation idempotent and outside application startup when possible to avoid coupling migrations and infra creation.

## Next actions

- Ensure `docker-compose.yml` includes a `kafka-init` helper that creates topics idempotently (see repo root). If not present, add it.
- Add a small `scripts/check-topics.sh` helper to validate topics and exit with non-zero on failure (useful in CI).
- Consider adding a Kubernetes Job manifest to create topics when deploying to a cluster.
