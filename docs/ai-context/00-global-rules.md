# Global Rules for AI Agent

These rules apply to all features and generated code.

---

## Configuration Rules

- All configuration MUST be defined in application.yml
- Hardcoded values are NOT allowed

### Properties Usage

- Use @ConfigurationProperties for grouped configurations
- Do NOT use @Value for multiple related properties

Structure:

config/properties/

---

## Constants Rules

- All constants MUST be centralized

Structure:

common/constants/ApplicationConstants

### Restrictions

- No magic numbers
- No duplicated literals

---

## Code Design

- Methods must be short
- One responsibility per method
- Prefer early returns
- Avoid deep nesting

---

## Pattern Usage

- Use patterns only when necessary
- Avoid over-engineering

---

## Logging

- Logs must clearly describe execution flow
- Must be readable externally
- Include:
    - traceId
    - clienteId

---

## Comments

- Professional language only
- No symbols or emojis

---

## Exception Handling

- Use custom exceptions
- Use global handler
- Do not expose internal errors

---

## Database

- Tables: snake_case
- Columns: snake_case
- Use native queries when needed

---

## Lombok

- Avoid @Data in entities
- Prefer @Getter and @Setter

---

## Quality

- Must pass SonarQube
- Must pass Checkstyle

---

## Additional Rules (IMPORTANT)

### No Hardcoded Values

- All configurable values must come from properties or constants

---

### Layer Responsibility

- Domain must not depend on infrastructure
- Controllers must not contain business logic
- Use cases must orchestrate logic

---

### Code Completeness

- Do not generate partial implementations
- Do not leave TODOs

---

## Final Rule

If code is not clean, readable and maintainable, refactor before returning.
