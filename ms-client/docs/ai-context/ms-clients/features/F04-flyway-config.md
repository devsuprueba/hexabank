# Feature: Flyway Configuration

## User Story
As a system,
I want controlled database migrations,
so that schema changes are versioned.

---

## Tasks

### 1. Configure Flyway

spring:
flyway:
enabled: true
locations: classpath:db/migration

---

### 2. Script Naming

Use:

V1__init_schema.sql
V2__add_indexes.sql

---

### 3. Script Location

src/main/resources/db/migration/

---

### 4. Baseline Strategy

- Do not modify existing scripts
- Always create new version

---

## Rules

- Scripts must be idempotent
- Naming must be consistent

---

## Acceptance Criteria

- Flyway runs at startup
- Schema is created correctly