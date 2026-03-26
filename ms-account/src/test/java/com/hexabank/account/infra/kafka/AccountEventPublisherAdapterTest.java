package com.hexabank.account.infra.kafka;

import com.hexabank.account.infra.kafka.model.AccountCreatedEvent;
import com.hexabank.account.infra.kafka.model.MovementCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountEventPublisherAdapterTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    

    @Test
    void publishAccountCreatedShouldSendToConfiguredTopic() {
        final AccountCreatedEvent event = new AccountCreatedEvent(1L, "client-1", null, Instant.now());

        final KafkaProperties kafkaProperties = new KafkaProperties();
        kafkaProperties.setAccountCreatedTopic("acct.created.test");
        final AccountEventPublisherAdapter adapter = new AccountEventPublisherAdapter(kafkaTemplate, kafkaProperties);

        adapter.publishAccountCreated(event);

        verify(kafkaTemplate).send("acct.created.test", String.valueOf(event.getEntityId()), event);
    }

    @Test
    void publishMovementCreatedShouldSendToConfiguredTopic() {
        final MovementCreatedEvent event = new MovementCreatedEvent(2L, "client-2", null, Instant.now());

        final KafkaProperties kafkaProperties = new KafkaProperties();
        kafkaProperties.setMovementCreatedTopic("movement.created.test");
        final AccountEventPublisherAdapter adapter = new AccountEventPublisherAdapter(kafkaTemplate, kafkaProperties);

        adapter.publishMovementCreated(event);

        verify(kafkaTemplate).send("movement.created.test", String.valueOf(event.getEntityId()), event);
    }
}
