# Feature: Kafka Provider

## User Story
As a system,
I want to publish events to Kafka,
so that other microservices can react asynchronously.

---

## Tasks

### 1. Configure Kafka Producer

- Use KafkaTemplate
- Configure serializers

---

### 2. Define Topic Configuration

- Topic name must come from application.yml
- Use @ConfigurationProperties

Example:
clients.events

---

### 3. Create Event Model

Define event structure:

- eventType
- clientId
- payload
- timestamp

---

### 4. Implement Event Publisher

- Create adapter implementing EventPublisherPort
- Send events using KafkaTemplate

---

## Logging

- Log event publication
- Include:
    - eventType
    - clientId

---

## Error Handling

- Handle send failures
- Log errors clearly

---

## Rules

- Do not hardcode topic names
- Use constants for event types
- Ensure serialization is correct

---

## Acceptance Criteria

- Events are published successfully
- Topic is correctly resolved
- Logs show event flow