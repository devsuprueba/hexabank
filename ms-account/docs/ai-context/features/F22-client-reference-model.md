# Feature: Client Reference Model

## User Story

As a system,
I want to reference the client in accounts,
so that accounts are correctly linked to a client.

---

## Tasks

- Ensure Account contains:
  - clientId

- Validate that clientId is mandatory

- (Optional) create lightweight ClientReference model (no persistence)

---

## Rules

- Do not duplicate full Client entity from ms-client
- Do not create client CRUD here
- Only reference clientId

---

## Acceptance Criteria

- Account is linked to clientId
- No duplicated client domain
