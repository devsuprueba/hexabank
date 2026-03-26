# Feature: Flyway Evolution

## User Story

As a system,
I want schema evolution,
so that future changes are supported without altering previous migrations.

---

## Tasks

Create new migration scripts such as:

- V2\_\_add_indexes.sql
- V3\_\_add_constraints.sql

Examples:

- index for account_number
- index for client_id
- constraints for amount and balances if needed

---

## Rules

- Do not modify previous scripts
- Always create a new migration
- Keep naming consistent

---

## Acceptance Criteria

- New migrations execute successfully
- Existing data is preserved
