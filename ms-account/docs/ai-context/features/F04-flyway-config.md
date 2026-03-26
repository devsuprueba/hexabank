# Feature: Flyway Configuration

## User Story

As a system,
I want controlled database migrations,
so that schema changes are versioned and reproducible.

---

## Tasks

- Configure Flyway in application.yml
- Define migration folder:
  src/main/resources/db/migration/
- Use versioned scripts

---

## Script Naming Convention

- V1\_\_init_schema.sql
- V2\_\_add_indexes.sql
- V3\_\_add_constraints.sql

---

## Rules

- Do not modify old scripts
- Always create a new migration for schema changes
- Keep naming consistent
- Schema must be reproducible from scratch

---

## Acceptance Criteria

- Flyway runs at startup
- Schema is created successfully
- Migration history is consistent
