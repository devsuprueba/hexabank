# Feature: Infrastructure Docker Compose

## User Story
As a system,
I want to run all services with one command,
so that the system is easy to deploy and test.

## Tasks

- Create docker-compose.yml
- Include:

    - Zookeeper
    - Kafka
    - PostgreSQL
    - ms-clients
    - ms-accounts

## Rules

- Use Docker internal networking
- Services must communicate using service names
- No localhost usage

## Configuration

Kafka:
- kafka:9092

Postgres:
- postgres:5432

## Acceptance Criteria

- System runs with:
  docker-compose up

- All services are reachable
- Kafka communication works
- Database is accessible