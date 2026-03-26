# Feature: Architecture Base

## User Story

As a developer,
I want to define the project structure,
so that the microservice follows hexagonal architecture.

---

## Tasks

Create base package structure:

- domain
  - model
  - exception
- application
  - usecase
  - service
  - port
- infrastructure
  - controller
  - repository
  - kafka
  - config
  - mapper
  - report

---

## Rules

- Domain must not depend on infrastructure
- Use cases belong to application layer
- Controllers must delegate to use cases
- Persistence models must remain in infrastructure

---

## Acceptance Criteria

- Clean architecture structure created
- No circular dependencies
- Layer responsibilities are clear
