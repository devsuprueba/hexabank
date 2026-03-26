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

### 2. Define Topic Configuration

Topic names must come from application.yml through @ConfigurationProperties.

### 3. Create Event Models

Create event structures for:

- AccountCreatedEvent
- MovementCreatedEvent

Each event must include:

- eventType
- entityId
- clientId if applicable
- payload
- timestamp

### 4. Implement Publisher Adapter

- Create adapter implementing AccountEventPublisherPort
- Send events using KafkaTemplate

---

## Rules

- Do not hardcode topic names
- Use constants for event types
- Log all published events
- Handle send failures gracefully

---

## Acceptance Criteria

- Events are published successfully
- Topic is resolved from configuration
- Logs show event publication flow
