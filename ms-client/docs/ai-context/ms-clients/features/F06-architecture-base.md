# Feature: Architecture Base

## User Story
As a developer,
I want to define the project structure,
so that the system follows hexagonal architecture.

## Tasks

Create base package structure:

- domain
    - model
    - exception

- application
    - usecase
    - service
    - port

- infrastructure
    - controller
    - repository
    - kafka
    - config

## Rules

- No dependency from domain to infrastructure
- Strict separation of layers

## Acceptance Criteria

- Clean architecture structure created
- No circular dependencies