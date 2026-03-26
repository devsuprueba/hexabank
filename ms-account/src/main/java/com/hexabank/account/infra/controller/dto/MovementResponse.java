package com.hexabank.account.infra.controller.dto;

import com.hexabank.account.domain.entity.Movement.MovementType;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record MovementResponse(Long id, MovementType movementType, BigDecimal amount, OffsetDateTime createdAt) {

}
