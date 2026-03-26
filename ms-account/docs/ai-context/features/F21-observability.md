# Feature: Observability

## User Story

As a system,
I want to expose metrics and logs,
so that I can monitor behavior and diagnose issues.

---

## Tasks

### 1. Enable Actuator

Enable endpoints such as:

- health
- metrics
- info
- prometheus

### 2. Prometheus Integration

- Expose Prometheus metrics endpoint

### 3. Logging

- Ensure logs are structured
- Include MDC context
- Log account, movement and reporting flows

---

## Rules

- Do not expose sensitive data
- Logs must be readable externally
- Monitoring must not interfere with business logic

---

## Acceptance Criteria

- Actuator endpoints are available
- Prometheus metrics are exposed
- Logs show execution flow clearly
