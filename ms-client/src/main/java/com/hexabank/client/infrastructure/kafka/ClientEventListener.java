package com.hexabank.client.infrastructure.kafka;

import com.hexabank.client.application.usecase.ProcessClientEventUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Profile("kafka")
public class ClientEventListener {

    private static final Logger log = LoggerFactory.getLogger(ClientEventListener.class);

    private final ProcessClientEventUseCase processor;

    public ClientEventListener(ProcessClientEventUseCase processor) {
        this.processor = processor;
    }

    @KafkaListener(topics = "#{@clientsKafkaProperties.events}")
    public void onMessage(ClientEvent event) {
        if (event == null) {
            log.warn("Received null event");
            return;
        }
        log.info("Received eventType={} clientId={}", event.getEventType(), event.getClientId());
        try {
            processor.execute(event);
        } catch (Exception e) {
            // log and swallow to avoid crashing the listener
            log.error("Error processing event {}", event, e);
        }
    }

}
