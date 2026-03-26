package com.hexabank.account.infra.kafka;

import java.time.Instant;

/**
 * Lightweight representation of client events emitted by ms-client.
 * Mirrors the structure used by ms-client so JSON deserialization works.
 */
public class ClientEvent {

    private String eventType;
    private Long clientId;
    private Object payload;
    private Instant timestamp;

    public ClientEvent() {
        this.timestamp = Instant.now();
    }

    public ClientEvent(final String eventType, final Long clientId, final Object payload) {
        this.eventType = eventType;
        this.clientId = clientId;
        this.payload = payload;
        this.timestamp = Instant.now();
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(final String eventType) {
        this.eventType = eventType;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(final Long clientId) {
        this.clientId = clientId;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(final Object payload) {
        this.payload = payload;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Instant timestamp) {
        this.timestamp = timestamp;
    }
}
