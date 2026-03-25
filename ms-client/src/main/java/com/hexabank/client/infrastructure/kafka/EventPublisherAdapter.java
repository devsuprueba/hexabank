package com.hexabank.client.infrastructure.kafka;

import com.hexabank.client.application.port.EventPublisherPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.hexabank.client.infrastructure.config.ClientsKafkaProperties;

/**
 * Kafka-backed implementation of EventPublisherPort. Active under 'kafka' profile.
 */
@Component
@Profile("kafka")
public class EventPublisherAdapter implements EventPublisherPort {

    private static final Logger log = LoggerFactory.getLogger(EventPublisherAdapter.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ClientsKafkaProperties props;

    public EventPublisherAdapter(KafkaTemplate<String, Object> kafkaTemplate, ClientsKafkaProperties props) {
        this.kafkaTemplate = kafkaTemplate;
        this.props = props;
    }

    @Override
    public void publish(String topic, Object event) {
        String resolvedTopic = topic == null || topic.isBlank()
                ? props.getEvents()
                : topic;

        String eventTypeStr = event instanceof ClientEvent ? ((ClientEvent) event).getEventType() : "?";
        String clientIdStr = event instanceof ClientEvent
                ? String.valueOf(((ClientEvent) event).getClientId())
                : "?";
        log.info("Publishing eventType={} clientId={} to topic={}",
                eventTypeStr, clientIdStr, resolvedTopic);
        try {
            kafkaTemplate.send(resolvedTopic, event).whenComplete((r, ex) -> {
                if (ex == null) {
                    try {
                        long partition = r.getRecordMetadata().partition();
                        log.info("Event sent to topic={} partition={}", resolvedTopic, partition);
                    } catch (Exception e) {
                        log.info("Event sent to topic={}", resolvedTopic);
                    }
                } else {
                    log.error("Failed to send event to topic={}", resolvedTopic, ex);
                }
            });
        } catch (Exception e) {
            log.error("Exception while sending event", e);
        }
    }

}
