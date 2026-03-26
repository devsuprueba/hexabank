# Feature: Flyway Initial Schema

## User Story

As a system,
I want to initialize the account schema,
so that accounts and movements can be persisted.

---

## Tasks

Create V1\_\_init_schema.sql with tables:

- account
- movement

---

## Required Columns

### account

- id
- client_id
- account_number
- account_type
- initial_balance
- current_balance
- status
- version
- created_at
- updated_at

### movement

- id
- account_id
- movement_date
- movement_type
- amount
- balance_after_movement
- created_at

---

## Rules

- Use snake_case
- Add primary keys
- Add foreign key from movement to account
- Add unique constraint on account_number
- Include version column for concurrency control

---

## Acceptance Criteria

- Schema created successfully
- Relationships are valid
- Constraints match domain rules
