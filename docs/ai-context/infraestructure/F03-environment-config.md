# Feature: Environment Configuration

## User Story

As a system,
I want centralized configuration,
so that services are configurable and maintainable.

## Tasks

- Define environment variables for:
  - Kafka
  - PostgreSQL
  - Application configs

## Rules

- No hardcoded values
- Use application.yml
- Use @ConfigurationProperties

## Examples

Kafka:

- bootstrap-servers: kafka:9092

Database:

- url: jdbc:postgresql://postgres:5432/hexabank

## Acceptance Criteria

- All services read configuration correctly
- No hardcoded configuration values
