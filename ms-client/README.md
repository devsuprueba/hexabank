# ms-client

This module contains the `ms-client` microservice for Hexabank.

Overview

- Java 21, Spring Boot 4.x, Gradle
- Hexagonal architecture: domain, application (use-cases), infrastructure (adapters)

Build & run (locally)

1. Build the executable jar:

   ./gradlew :ms-client:bootJar

2. Run with in-memory DB for local development:

   java -Dspring.datasource.url=jdbc:h2:mem:testdb -Dspring.datasource.driver-class-name=org.h2.Driver -Dspring.datasource.username=sa -Dspring.datasource.password= -jar build/libs/client-0.0.1-SNAPSHOT.jar

Docker

- A `Dockerfile` is available at the module root. Build the image after creating the jar:

  ./gradlew bootJar
  docker build -t hexabank-ms-client:local .

Kubernetes

- Manifests are in `k8s/` with a Deployment and a Service. The Deployment reads configuration from environment variables and includes liveness/readiness probes.

Observability

- Actuator endpoints enabled: `/actuator/health`, `/actuator/metrics`, `/actuator/prometheus` (Prometheus available when micrometer-registry-prometheus is on the classpath)
- Prometheus scrape sample: `k8s/prometheus-scrape.yaml`

Notes

- Do not hardcode secrets in manifests. Use Kubernetes Secrets for DB passwords.
- For local runs without Kafka, a no-op event publisher is available under non-kafka profiles. Use the `kafka` profile to enable Kafka-backed publisher.
