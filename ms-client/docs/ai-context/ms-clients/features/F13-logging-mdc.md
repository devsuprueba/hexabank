# Feature: Logging MDC

## User Story
As a developer,
I want to trace execution flow,
so that debugging and monitoring is easier.

## Tasks

- Implement MDC context:
    - traceId
    - clienteId

- Add logs in:
    - controllers
    - use cases
    - repositories (only relevant operations)

## Rules

- Logs must be readable
- Logs must describe business flow
- Avoid excessive logs

## Acceptance Criteria

- Logs contain context information
- Execution flow can be traced