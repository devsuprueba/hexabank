package com.hexabank.account.infra.kafka.model;

import java.time.Instant;

/**
 * Event published when an account is created.
 */
public final class AccountCreatedEvent {

    public static final String EVENT_TYPE = "AccountCreated";
    private final Long entityId;
    private final String clientId;
    private final Object payload;
    private final Instant timestamp;

    public AccountCreatedEvent(final Long entityId,
                               final String clientId,
                               final Object payload,
                               final Instant timestamp) {
        this.entityId = entityId;
        this.clientId = clientId;
        this.payload = payload;
        this.timestamp = timestamp;
    }

    public String getEventType() {
        return EVENT_TYPE;
    }

    public Long getEntityId() {
        return entityId;
    }

    public String getClientId() {
        return clientId;
    }

    public Object getPayload() {
        return payload;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
