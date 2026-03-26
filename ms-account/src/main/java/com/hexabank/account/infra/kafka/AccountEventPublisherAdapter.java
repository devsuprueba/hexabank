package com.hexabank.account.infra.kafka;

import com.hexabank.account.application.port.AccountEventPublisherPort;
import com.hexabank.account.infra.kafka.model.AccountCreatedEvent;
import com.hexabank.account.infra.kafka.model.MovementCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
 

/**
 * Adapter that publishes account and movement events to Kafka using the configured topics.
 */
@Component
public class AccountEventPublisherAdapter implements AccountEventPublisherPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountEventPublisherAdapter.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaProperties kafkaProperties;
    public AccountEventPublisherAdapter(final KafkaTemplate<String, Object> kafkaTemplate,
                                         final KafkaProperties kafkaProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaProperties = kafkaProperties;
    }

    @Override
    public void publishAccountCreated(final AccountCreatedEvent event) {
        final String topic = kafkaProperties.getAccountCreatedTopic();
        LOGGER.info("Publishing AccountCreatedEvent to topic {} for entity {}", topic, event.getEntityId());

        try {
            kafkaTemplate.send(topic, String.valueOf(event.getEntityId()), event);
            LOGGER.info(
                    "Queued AccountCreatedEvent for entity {} to topic {}",
                    event.getEntityId(), topic);
        } catch (final Exception ex) {
            LOGGER.error(
                    "Failed to send AccountCreatedEvent for entity {} to topic {}",
                    event.getEntityId(), topic, ex);
        }
    }

    @Override
    public void publishMovementCreated(final MovementCreatedEvent event) {
        final String topic = kafkaProperties.getMovementCreatedTopic();
        LOGGER.info("Publishing MovementCreatedEvent to topic {} for entity {}", topic, event.getEntityId());

        try {
            kafkaTemplate.send(topic, String.valueOf(event.getEntityId()), event);
            LOGGER.info(
                    "Queued MovementCreatedEvent for entity {} to topic {}",
                    event.getEntityId(), topic);
        } catch (final Exception ex) {
            LOGGER.error(
                    "Failed to send MovementCreatedEvent for entity {} to topic {}",
                    event.getEntityId(), topic, ex);
        }
    }
}
