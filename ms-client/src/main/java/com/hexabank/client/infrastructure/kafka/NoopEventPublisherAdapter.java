package com.hexabank.client.infrastructure.kafka;

import com.hexabank.client.application.port.EventPublisherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * No-op EventPublisher used when Kafka is not enabled. Active for profiles other than
 * 'kafka' so the application can start locally without Kafka broker.
 */
@Component
@Profile("!kafka")
public class NoopEventPublisherAdapter implements EventPublisherPort {

    private static final Logger log = LoggerFactory.getLogger(NoopEventPublisherAdapter.class);

    @Override
    public void publish(String topic, Object event) {
        if (log.isDebugEnabled()) {
            log.debug("No-op publish to topic={} event={}", topic, event);
        }
    }

}
