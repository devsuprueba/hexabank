# Feature: JPA Configuration

## User Story

As a system,
I want to configure persistence,
so that data is stored efficiently and correctly.

---

## Tasks

### 1. Configure DataSource

Use PostgreSQL with properties defined in application.yml.

---

### 2. Configure JPA

- Disable ddl-auto schema generation
- Use Flyway as the source of truth
- Disable unnecessary SQL logs

---

### 3. Entity Mapping

- Use @Entity only in infrastructure layer
- Explicitly map table and column names
- Use snake_case naming

---

### 4. Native Queries

- Use native queries when needed for reports or optimized reads
- Keep query names clear and intention-revealing

---

## Rules

- Do not rely on default table naming
- Avoid N+1 queries
- Map domain and persistence models separately

---

## Acceptance Criteria

- Data persists correctly
- Repositories can execute native queries
- Mappings are explicit and stable
