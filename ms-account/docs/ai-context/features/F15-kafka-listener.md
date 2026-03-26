# Feature: Kafka Listener

## User Story

As a system,
I want to consume events from Kafka,
so that the microservice can react to changes in other bounded contexts.

---

## Tasks

### 1. Configure Consumer

- Define group-id
- Configure deserializer
- Load topic names from configuration

### 2. Implement Listeners

Consume events from ms-clients when needed, for example:

- ClientCreatedEvent
- ClientUpdatedEvent
- ClientDeletedEvent

### 3. Event Processing

- Validate incoming event
- Delegate processing to application layer
- Update local references if needed

---

## Rules

- Listener must not contain business logic
- Do not block listener thread with complex logic
- Handle deserialization and processing failures cleanly

---

## Acceptance Criteria

- Events are consumed correctly
- Processing is delegated properly
- Listener remains stable on failures
