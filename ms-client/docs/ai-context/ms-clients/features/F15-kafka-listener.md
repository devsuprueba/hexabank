# Feature: Kafka Listener

## User Story
As a system,
I want to consume events from Kafka,
so that I can react to external changes.

---

## Tasks

### 1. Configure Consumer

- Define group-id
- Configure deserializer

---

### 2. Create Listener

- Use @KafkaListener
- Subscribe to topic from configuration

---

### 3. Event Processing

- Deserialize event
- Validate event content
- Execute corresponding logic

---

## Logging

- Log received event
- Include:
    - eventType
    - clientId

---

## Error Handling

- Handle deserialization errors
- Log processing failures
- Avoid crashing listener

---

## Rules

- Do not block listener thread
- Keep processing logic simple
- Delegate logic to application layer

---

## Acceptance Criteria

- Events are consumed correctly
- Listener is stable
- Errors are handled gracefully