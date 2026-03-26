package com.hexabank.account.infra.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = KafkaPropertiesTest.TestConfig.class)
@TestPropertySource(properties = {
        "hexabank.kafka.account-created-topic=acct.created",
        "hexabank.kafka.client-created-topic=cliente.created.test",
        "hexabank.kafka.consumer-group-id=test-group"
})
public class KafkaPropertiesTest {

    @Configuration
    @EnableConfigurationProperties(KafkaProperties.class)
    static class TestConfig {
    }

    @Autowired
    private KafkaProperties kafkaProperties;

    @Test
    void propertiesAreBoundFromConfiguration() {
        assertEquals("acct.created", kafkaProperties.getAccountCreatedTopic());
        assertEquals("cliente.created.test", kafkaProperties.getClientCreatedTopic());
        assertEquals("test-group", kafkaProperties.getConsumerGroupId());
    }
}
