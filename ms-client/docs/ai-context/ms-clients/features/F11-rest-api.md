# Feature: REST API

## User Story
As a user,
I want to interact with the system via HTTP,
so that I can manage clients.

---

## Tasks

### 1. Create Controller

- Use @RestController
- Define endpoints

---

### 2. Endpoints

POST /clients
GET /clients
PUT /clients/{id}
DELETE /clients/{id}

---

### 3. DTOs

Create:

- ClientRequestDTO
- ClientResponseDTO

---

### 4. Mapping

Map DTO ↔ Domain

---

## Example

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final CreateClientUseCase useCase;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@RequestBody ClientRequestDTO request) {
        return ResponseEntity.ok(useCase.execute(request));
    }
}

---

## Validation

- Validate request data
- Use @Valid

---

## Logging

- Log incoming requests
- Log responses

---

## Rules

- No business logic in controller
- Delegate to use cases

---

## Acceptance Criteria

- Endpoints work correctly
- Requests validated
- Responses consistent