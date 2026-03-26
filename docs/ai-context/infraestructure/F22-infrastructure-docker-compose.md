# Feature: Infrastructure Docker Compose

## User Story

As a system,
I want to run all services with one command,
so that the system is easy to deploy and test.

---

## Tasks

- Create docker-compose.yml
- Include:
  - Zookeeper
  - Kafka
  - Kafka topic initializer
  - PostgreSQL
  - ms-clients
  - ms-accounts

---

## Database Initialization

- PostgreSQL must create the database automatically using environment variables:
  - POSTGRES_DB
  - POSTGRES_USER
  - POSTGRES_PASSWORD

- Schema must be created by Flyway migrations on application startup

---

## Kafka Topics

The system must automatically create required topics:

- account-created
- movement-created

---

## Rules

- Use Docker internal networking
- Services must communicate using service names
- No localhost usage
- Topics must be created automatically during startup
- Topic creation must be idempotent
- Database must be initialized automatically
- Schema must not be created manually outside Flyway

---

## Configuration

Kafka:

- kafka:9092

Postgres:

- postgres:5432

---

## Acceptance Criteria

- System runs with:
  docker-compose up

- All services are reachable
- Kafka communication works
- Topics are created automatically
- Database is created automatically
- Schema is created via Flyway
- No manual setup required
