# Feature: REST API

## User Story

As a user,
I want to interact with accounts and movements via HTTP,
so that I can manage accounts and request reports.

---

## Tasks

Create controllers and DTOs for:

### Accounts

- POST /accounts
- GET /accounts
- GET /accounts/{id}

### Movements

- POST /movements
- GET /movements

### Reports

- GET /reports
- GET /reports/excel

---

## Request/Response Rules

- Use DTOs for request and response
- Validate input with annotations
- Keep response structure consistent

---

## Report Parameters

- clientId
- startDate
- endDate

---

## Rules

- Controllers must not contain business logic
- Use cases must be called from controllers
- Report endpoints belong to this microservice

---

## Acceptance Criteria

- Endpoints work correctly
- Input is validated
- Report and Excel endpoints are available
