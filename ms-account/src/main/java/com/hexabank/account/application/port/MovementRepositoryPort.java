package com.hexabank.account.application.port;

import com.hexabank.account.domain.entity.Movement;

import java.util.List;
import java.util.Optional;

public interface MovementRepositoryPort {

    Movement save(Movement movement);

    Optional<Movement> findById(Long id);

    List<Movement> findByAccountId(Long accountId);
}
