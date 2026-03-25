# Feature: Observability

## User Story
As a system,
I want to expose metrics and logs,
so that I can monitor behavior.

---

## Tasks

### 1. Enable Actuator

- Enable endpoints:
    - health
    - metrics
    - info

---

### 2. Prometheus Integration

- Expose metrics endpoint

---

### 3. Logging

- Ensure logs are structured
- Include MDC context

---

## Rules

- Do not expose sensitive data
- Logs must be readable externally

---

## Acceptance Criteria

- Actuator endpoints available
- Metrics exposed
- Logs show execution flow