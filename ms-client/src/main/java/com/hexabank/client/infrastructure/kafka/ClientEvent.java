package com.hexabank.client.infrastructure.kafka;

import java.time.Instant;

public class ClientEvent {

    private String eventType;
    private Long clientId;
    private Object payload;
    private Instant timestamp;

    public ClientEvent() {
        this.timestamp = Instant.now();
    }

    public ClientEvent(String eventType, Long clientId, Object payload) {
        this.eventType = eventType;
        this.clientId = clientId;
        this.payload = payload;
        this.timestamp = Instant.now();
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
