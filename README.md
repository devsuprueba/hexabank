# Hexabank — local development bootstrap

This repository contains the Hexabank microservices and helper infra to run a local development stack (Postgres, Zookeeper, Kafka, and the microservices ms-client and ms-account).

Requirements

- Docker (desktop or engine) - tested with Docker Engine and Compose V2
- docker-compose (v1.29+ or the Docker Compose V2 plugin)
- Internet access to pull images
- Java/Gradle is only required to build the JARs if you don't have prebuilt artifacts (script uses the module Gradle wrappers)

Quick start (recommended)

1. From the repo root, run the helper script (it will build jars, pull images and start everything):

```bash
./run-local-dev.sh
```

2. After the script finishes, verify:

```bash
curl -sS http://localhost:8081/actuator/health
curl -sS http://localhost:8082/actuator/health
docker-compose exec kafka /usr/bin/kafka-topics --bootstrap-server kafka:9092 --list
```

Notes and behaviour

- The script will copy `.env.example` to `.env` if `.env` is not present.
- The script builds the module JARs using each module's Gradle wrapper (`ms-client/gradlew`, `ms-account/gradlew`). If you prefer to build separately, you can run:

```bash
./ms-client/gradlew :ms-client:bootJar -x test
./ms-account/gradlew :ms-account:bootJar -x test
```

- Flyway migration strategy: to avoid collisions when multiple services share the same DB in dev, each service uses an isolated Flyway schema history table (configured via `SPRING_FLYWAY_TABLE` in `docker-compose.yml`).

- If you have an existing Postgres volume with previous data, Flyway may require `baseline()` or `repair` — this script assumes a fresh DB. In that edge case, remove the Docker volume used for Postgres or follow the Flyway repair procedure documented in `docs/`.

Files added

- `./run-local-dev.sh` — helper script to bootstrap the local environment (build + compose).
- `./README.md` — this quick-start and requirements file (root).

If you prefer a different flow (e.g. separate DBs per service or stricter migration policies), let me know and I can update the compose/setup accordingly.
