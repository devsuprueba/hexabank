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
    private String clientCreatedTopic = "cliente.created";
    private String clientUpdatedTopic = "cliente.updated";
    private String clientDeletedTopic = "cliente.deleted";
    private String consumerGroupId = "account-service-group";

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

    public String getClientCreatedTopic() {
        return clientCreatedTopic;
    }

    public void setClientCreatedTopic(String clientCreatedTopic) {
        this.clientCreatedTopic = clientCreatedTopic;
    }

    public String getClientUpdatedTopic() {
        return clientUpdatedTopic;
    }

    public void setClientUpdatedTopic(String clientUpdatedTopic) {
        this.clientUpdatedTopic = clientUpdatedTopic;
    }

    public String getClientDeletedTopic() {
        return clientDeletedTopic;
    }

    public void setClientDeletedTopic(String clientDeletedTopic) {
        this.clientDeletedTopic = clientDeletedTopic;
    }

    public String getConsumerGroupId() {
        return consumerGroupId;
    }

    public void setConsumerGroupId(String consumerGroupId) {
        this.consumerGroupId = consumerGroupId;
    }
}
