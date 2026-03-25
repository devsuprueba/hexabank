# Feature: Testing

## User Story
As a developer,
I want to validate the system,
so that it behaves correctly under different scenarios.

---

## Tasks

### 1. Unit Tests

- Test domain logic
- Test use cases

---

### 2. Integration Tests

- Test REST endpoints
- Test repository layer

---

### 3. Test Coverage

- Cover main flows:
    - create client
    - update client
    - delete client

---

## Tools

- JUnit 5
- Mockito

---

## Rules

- Tests must be deterministic
- Avoid unnecessary mocks
- Focus on behavior

---

## Optional

- Mock Kafka interactions

---

## Acceptance Criteria

- Tests run successfully
- Core logic is covered
- No flaky tests