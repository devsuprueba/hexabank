# Feature: Domain Model

## User Story

As a developer,
I want to define domain entities,
so that business rules are encapsulated and independent from infrastructure.

---

## Tasks

### 1. Define Domain Entities

Create domain models:

- Account
- Movement

---

## Account Fields

- id
- clientId
- accountNumber
- accountType
- initialBalance
- currentBalance
- status

---

## Movement Fields

- id
- accountId
- movementDate
- movementType
- amount
- balanceAfterMovement

---

## Business Rules

- Account must have a valid clientId
- Account number must be mandatory
- Initial balance must be initialized
- Deposits use positive values
- Withdrawals use negative values
- A withdrawal cannot leave the balance below zero
- Invalid state must not be allowed

---

## Design Rules

- No framework annotations
- No JPA annotations
- No Spring annotations
- No logging in domain
- Objects must not be created in invalid state

---

## Acceptance Criteria

- Domain entities are framework-independent
- Rules are encapsulated in domain
- Invalid state is prevented
