package com.hexabank.client.infrastructure.kafka;

import com.hexabank.client.application.port.EventPublisherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Simple infrastructure adapter for EventPublisherPort.
 * For now it logs events; a future feature will replace it with a Kafka implementation.
 */
@Component
@Profile("!test")
public class EventPublisherAdapter implements EventPublisherPort {

    private static final Logger log = LoggerFactory.getLogger(EventPublisherAdapter.class);

    @Override
    public void publish(String topic, Object event) {
        log.info("Publishing event to topic={} payload={}", topic, event);
        // No-op for now; Kafka provider to be implemented in later feature.
    }

}
