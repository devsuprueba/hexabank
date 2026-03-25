package com.hexabank.client.infrastructure.kafka;

public interface EventPublisher {

    void publish(String topic, Object event);

}
