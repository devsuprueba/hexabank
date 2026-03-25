# Architecture Definition

## Microservice

ms-clients

## Responsibilities

- Manage Persona and Cliente
- Publish client events
- Generate reports (JSON and Excel)

## Architecture Style

Hexagonal Architecture

## Layers

Domain:
- Entities
- Business rules
- Exceptions

Application:
- Use cases
- Services
- Ports

Infrastructure:
- Controllers
- JPA Repositories
- Kafka Producers
- Excel generator

## Communication

- Event-driven using Kafka
- Events:
    - ClienteCreated
    - ClienteUpdated
    - ClienteDeleted

## Persistence

- PostgreSQL
- Flyway migrations
- Native queries when required

## Observability

- MDC logging
- Actuator
- Prometheus

## Principles

- Clean Architecture
- SOLID
- Separation of concerns