package com.hexabank.account.infra.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Holds Kafka topic names loaded from configuration.
 */
@Component
@ConfigurationProperties(prefix = "hexabank.kafka")
public class KafkaProperties {

    private String accountCreatedTopic = "account.created";
    private String movementCreatedTopic = "movement.created";

    public String getAccountCreatedTopic() {
        return accountCreatedTopic;
    }

    public void setAccountCreatedTopic(String accountCreatedTopic) {
        this.accountCreatedTopic = accountCreatedTopic;
    }

    public String getMovementCreatedTopic() {
        return movementCreatedTopic;
    }

    public void setMovementCreatedTopic(String movementCreatedTopic) {
        this.movementCreatedTopic = movementCreatedTopic;
    }
}
