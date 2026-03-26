# Feature: Repository Layer

## User Story

As a developer,
I want to persist domain entities,
so that data is stored correctly.

---

## Tasks

- Create JPA entities for:
  - account
  - movement
- Create mappers between domain and persistence models
- Implement repositories
- Add native queries for reporting scenarios

---

## Rules

- Keep persistence models in infrastructure
- Use explicit mappings
- Use native queries where they improve report generation
- Avoid N+1 problems

---

## Acceptance Criteria

- CRUD operations work correctly
- Mapping between entity and domain is consistent
- Report-oriented queries are available
