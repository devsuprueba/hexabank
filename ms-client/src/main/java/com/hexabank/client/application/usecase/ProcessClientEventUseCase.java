package com.hexabank.client.application.usecase;

import com.hexabank.client.infrastructure.kafka.ClientEvent;
import com.hexabank.client.infrastructure.kafka.EventTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Minimal processor for client events. Delegates or logs based on event type.
 */
@Component
public class ProcessClientEventUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProcessClientEventUseCase.class);

    public void execute(ClientEvent event) {
        if (event.getEventType() == null) {
            log.warn("Event without type received: {}", event);
            return;
        }
        switch (event.getEventType()) {
            case EventTypes.CLIENT_CREATED:
                log.info("Process client created event for id={}", event.getClientId());
                // currently we only log the event; in future this can call application services
                break;
            case EventTypes.CLIENT_UPDATED:
                log.info("Process client updated event for id={}", event.getClientId());
                break;
            case EventTypes.CLIENT_DELETED:
                log.info("Process client deleted event for id={}", event.getClientId());
                break;
            default:
                log.info("Unknown event type {} for client {}", event.getEventType(), event.getClientId());
        }
    }

}
