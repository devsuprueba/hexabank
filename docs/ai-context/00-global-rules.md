# Global Rules for AI Agent

These rules apply to all features and generated code.

---

## Architecture

- Follow Hexagonal Architecture
- Domain layer must be independent
- Application layer orchestrates logic
- Infrastructure implements adapters

---

## Code Design

- Methods must be short
- One responsibility per method
- Prefer early returns
- Avoid deep nesting
- Use meaningful names

---

## Patterns

- Use patterns only when necessary
- Avoid over-engineering

---

## Configuration Rules

- All configuration MUST be defined in application.yml
- Hardcoded values are NOT allowed

### Properties Usage

- Use @ConfigurationProperties for grouped configurations
- Do NOT use @Value for multiple related properties

---

## Constants Rules

- All constants MUST be centralized

Structure:

common/constants/ApplicationConstants

---

## Logging

- Logs must describe execution flow clearly
- Must be readable externally
- Must include:
    - traceId
    - business identifiers (clientId, etc.)
- Do not use System.out

---

## Exception Handling

- Use custom exceptions
- Use global exception handler
- Do not expose internal errors

---

## REST API Rules

- Controllers must not contain business logic
- Use DTOs for requests and responses
- Validate input using annotations
- Always return consistent responses

---

## Kafka Rules

- Topics must come from configuration
- Use structured event objects
- Log all produced and consumed events
- Handle errors gracefully

---

## Database Rules

- Tables: snake_case
- Columns: snake_case
- Use explicit mapping
- Prefer native queries when needed

---

## Lombok

- Avoid @Data in entities
- Prefer @Getter and @Setter

---

## Testing Rules

- Write unit tests for use cases
- Write integration tests for APIs
- Tests must be deterministic

---

## Code Quality

- Must pass SonarQube
- Must pass Checkstyle

---

## No Hardcoding

- No magic numbers
- No duplicated literals

---

## Code Completeness

- Do not generate partial implementations
- Do not leave TODOs

---

## Final Rule

If code is not clean, readable and maintainable, refactor before returning.
