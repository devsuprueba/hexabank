# Feature: Application Use Cases

## User Story
As a system,
I want to implement business logic through use cases,
so that domain rules are correctly orchestrated.

---

## Tasks

- Create use case classes:

    - CreateClientUseCase
    - UpdateClientUseCase
    - DeleteClientUseCase
    - GetClientUseCase

---

## Architecture Rules

- Use cases belong to application layer
- Must depend only on domain and ports
- Must NOT depend on infrastructure

---

## Ports

Define interfaces:

- ClientRepositoryPort
- EventPublisherPort

---

## Responsibilities

- Validate input data
- Call domain logic
- Persist data through ports
- Publish events when needed

---

## Design Rules

- One use case per class
- Methods must be short and clear
- Use constructor injection
- No business logic in controllers

---

## Logging

- Log start and end of use case
- Include relevant identifiers

---

## Acceptance Criteria

- Use cases are independent of infrastructure
- Business flow is correctly implemented
- Events are triggered when required