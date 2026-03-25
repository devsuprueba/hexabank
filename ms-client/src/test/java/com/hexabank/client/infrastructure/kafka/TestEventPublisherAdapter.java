package com.hexabank.client.infrastructure.kafka;

import com.hexabank.client.application.port.EventPublisherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestEventPublisherAdapter implements EventPublisherPort {

    private static final Logger log = LoggerFactory.getLogger(TestEventPublisherAdapter.class);

    @Override
    public void publish(String topic, Object event) {
        // For tests we just log the event
        log.debug("[test] publish topic={} event={}", topic, event);
    }

}
