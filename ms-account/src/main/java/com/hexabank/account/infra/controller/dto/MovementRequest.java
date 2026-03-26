package com.hexabank.account.infra.controller.dto;

import com.hexabank.account.domain.entity.Movement.MovementType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class MovementRequest {

    @NotNull
    private MovementType movementType;

    @NotNull
    private BigDecimal amount;

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
