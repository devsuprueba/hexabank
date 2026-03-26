# Feature: Exception Handling

## User Story

As a system,
I want to handle errors properly,
so that users receive clear and controlled responses.

---

## Tasks

Create domain exceptions such as:

- AccountNotFoundException
- MovementNotFoundException
- InsufficientBalanceException
- InvalidMovementException
- InvalidAccountStateException

Create a global exception handler.

---

## Rules

- Do not use generic Exception for domain errors
- Map business exceptions to appropriate HTTP responses
- Keep error response structure consistent

---

## Acceptance Criteria

- Standard error response implemented
- "Saldo no disponible" is returned for insufficient balance
- Internal errors are not exposed directly
