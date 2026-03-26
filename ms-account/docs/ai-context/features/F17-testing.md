# Feature: Testing

## User Story

As a developer,
I want to validate the system behavior,
so that account and movement flows are reliable.

---

## Tasks

### 1. Unit Tests

Create unit tests for:

- Account domain rules
- Movement domain rules
- RegisterMovementUseCase

### 2. Integration Tests

Create integration tests for:

- POST /accounts
- POST /movements
- GET /reports

### 3. Business Cases to Cover

- account creation
- deposit registration
- withdrawal registration
- insufficient balance scenario
- report generation

---

## Rules

- Tests must be deterministic
- Focus on behavior, not implementation details
- Use descriptive test names
- Use test database configuration

---

## Acceptance Criteria

- Unit and integration tests pass
- Main business scenarios are covered
