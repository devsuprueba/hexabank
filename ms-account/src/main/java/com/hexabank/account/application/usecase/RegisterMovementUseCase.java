package com.hexabank.account.application.usecase;

import com.hexabank.account.domain.entity.Movement;
import com.hexabank.account.domain.entity.Movement.MovementType;

import java.math.BigDecimal;
import java.util.Optional;

public interface RegisterMovementUseCase {

    Optional<Movement> register(Long accountId, MovementType type, BigDecimal amount);
}
