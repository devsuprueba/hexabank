# Feature: Exception Handling

## User Story
As a system,
I want to handle errors properly,
so that users receive clear responses.

## Tasks

- Create domain exceptions:
    - ClienteNotFoundException
    - ClienteInactiveException
    - InvalidRequestException

- Implement global exception handler

## Rules

- No generic Exception usage
- All errors must be mapped to responses

## Acceptance Criteria

- Standard error response implemented
- Errors are meaningful