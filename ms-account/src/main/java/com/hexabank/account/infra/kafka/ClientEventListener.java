package com.hexabank.account.infra.kafka;

import com.hexabank.account.application.usecase.ProcessClientEventUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka listener for client events coming from ms-client. Delegates processing to the
 * application layer use case so business logic is kept outside the listener.
 */
@Component
public class ClientEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientEventListener.class);

    private final ProcessClientEventUseCase processor;

    public ClientEventListener(final ProcessClientEventUseCase processor) {
        this.processor = processor;
    }

    @KafkaListener(
            topics = {"${hexabank.kafka.client-created-topic:cliente.created}",
                      "${hexabank.kafka.client-updated-topic:cliente.updated}",
                      "${hexabank.kafka.client-deleted-topic:cliente.deleted}"},
            groupId = "${hexabank.kafka.consumer-group-id:account-service-group}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void onMessage(final ClientEvent event) {
        if (event == null) {
            LOGGER.warn("Received null client event, ignoring");
            return;
        }

        try {
            processor.execute(event);
        } catch (final Exception ex) {
            // Keep listener stable on failures — log and continue. Retry or DLQ can be added later.
            LOGGER.error("Failed to process client event: {}", event, ex);
        }
    }
}
