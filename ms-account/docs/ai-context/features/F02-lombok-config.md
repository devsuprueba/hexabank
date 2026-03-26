# Feature: Lombok Configuration

## User Story

As a developer,
I want to configure Lombok correctly,
so that boilerplate code is reduced without affecting maintainability.

---

## Tasks

- Add Lombok dependency
- Add Lombok annotationProcessor
- Ensure IDE compatibility
- Use Lombok only where it improves readability

---

## Rules

- Avoid @Data in entities
- Prefer:
  - @Getter
  - @Setter
  - @Builder when justified
  - @RequiredArgsConstructor
- Do not hide business logic behind Lombok

---

## Acceptance Criteria

- Lombok is working correctly
- Code remains clear and maintainable
