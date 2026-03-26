package com.hexabank.account.application.usecase;

import com.hexabank.account.infra.kafka.ClientEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Application-level use case that processes client events consumed from Kafka. This keeps the
 * listener thin and delegates any business decisions to the application layer.
 */
@Service
public class ProcessClientEventUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessClientEventUseCase.class);

    public void execute(final ClientEvent event) {
        if (event == null) {
            LOGGER.warn("ProcessClientEventUseCase called with null event");
            return;
        }

        // Minimal implementation for now: validate and log. Real business logic (update local
        // references, emit new events, etc.) should be implemented here.
        LOGGER.info("Processing client event type={} clientId={}", event.getEventType(), event.getClientId());
    }
}
