package com.hexabank.client.application.port;

public interface EventPublisherPort {

    void publish(String topic, Object event);

}
