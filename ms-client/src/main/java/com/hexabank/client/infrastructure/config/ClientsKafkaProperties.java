package com.hexabank.client.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Binds kafka topic configuration under prefix 'clients'.
 * Example in application.yml:
 * clients:
 *   events: clients.events
 */
@Component
@ConfigurationProperties(prefix = "clients")
public class ClientsKafkaProperties {

    /** Topic name for client events */
    private String events;

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }
}
