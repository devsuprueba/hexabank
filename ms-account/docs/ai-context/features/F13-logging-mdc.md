# Feature: Logging MDC

## User Story

As a developer,
I want to trace execution flow,
so that debugging and operational monitoring are easier.

---

## Tasks

- Implement MDC context with:
  - traceId
  - clientId when available
  - accountNumber when available
- Add logs in:
  - controllers
  - use cases
  - kafka adapters
  - report generation flow

---

## Rules

- Logs must be readable externally
- Logs must describe business flow clearly
- Do not log sensitive data
- Avoid excessive logs

---

## Acceptance Criteria

- Logs contain relevant context
- Execution flow can be traced end to end
