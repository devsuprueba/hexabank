package com.hexabank.account.application.port;

import com.hexabank.account.infra.kafka.model.AccountCreatedEvent;
import com.hexabank.account.infra.kafka.model.MovementCreatedEvent;

/**
 * Port for publishing domain events related to accounts and movements.
 */
public interface AccountEventPublisherPort {

    void publishAccountCreated(AccountCreatedEvent event);

    void publishMovementCreated(MovementCreatedEvent event);

}
