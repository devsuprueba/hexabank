# Feature: Client API

## User Story

As a user,
I want to manage clients through a REST API,
so that I can create, retrieve, update and delete client information.

---

## Endpoints

### Create Client

POST /clients

### Get All Clients

GET /clients

### Get Client by ID

GET /clients/{id}

### Update Client

PUT /clients/{id}

### Delete Client

DELETE /clients/{id}

---

## Request/Response

### Create/Update Request

{
"name": "Juan Perez",
"gender": "M",
"age": 30,
"identification": "1234567890",
"address": "Calle 1",
"phone": "3001234567",
"status": true
}

---

### Response

{
"id": 1,
"name": "Juan Perez",
"status": true
}

---

## Tasks

- Create ClientController
- Create DTOs:
  - ClientRequestDto
  - ClientResponseDto
- Implement endpoints using use cases
- Add request validation
- Map domain ↔ DTO

---

## Validation Rules

- name must not be null or empty
- identification must be unique
- age must be positive
- status must be defined

---

## Error Handling

- Return 404 when client not found
- Return 400 for invalid input
- Use global exception handler

---

## Logging

- Log incoming requests
- Log key actions (create, update, delete)
- Include traceId in logs

---

## Rules

- Controllers must not contain business logic
- Use DTOs only (no domain exposure)
- Keep responses consistent
- No hardcoded values

---

## Acceptance Criteria

- All endpoints respond correctly
- Validation is enforced
- Errors are handled properly
- API is testable via Postman
- Logs show execution flow clearly
