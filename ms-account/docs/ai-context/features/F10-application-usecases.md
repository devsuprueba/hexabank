# Feature: Application Use Cases

## User Story

As a system,
I want to implement business logic through use cases,
so that account and movement flows are orchestrated correctly.

---

## Tasks

Create use cases:

- CreateAccountUseCase
- GetAccountUseCase
- GetAccountsUseCase
- RegisterMovementUseCase
- GetMovementsUseCase
- GenerateAccountStatementUseCase
- GenerateAccountStatementExcelUseCase

---

## Ports

Define interfaces:

- AccountRepositoryPort
- MovementRepositoryPort
- AccountEventPublisherPort
- ClientReferencePort if needed for validation

---

## Responsibilities

- Validate request data
- Orchestrate domain rules
- Persist changes
- Publish events when applicable
- Delegate reporting generation

---

## Rules

- No infrastructure dependency in use cases
- No business logic in controllers
- Methods must be short and focused
- Financial operations must be atomic

---

## Acceptance Criteria

- Business flows are implemented in use cases
- Layer responsibilities are respected
- Reporting use cases are available
