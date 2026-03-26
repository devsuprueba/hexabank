package com.hexabank.account.domain.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Framework-independent domain model for Movement.
 */
public class Movement {

    public enum MovementType {
        DEPOSIT, WITHDRAWAL
    }

    private Long id;
    private final Long accountId;
    private final OffsetDateTime movementDate;
    private final MovementType movementType;
    private final BigDecimal amount;
    private final BigDecimal balanceAfterMovement;

    public Movement(Long accountId, OffsetDateTime movementDate, MovementType movementType, BigDecimal amount, BigDecimal balanceAfterMovement) {
        this.accountId = accountId;
        this.movementDate = movementDate == null ? OffsetDateTime.now() : movementDate;
        this.movementType = movementType;
        this.amount = amount;
        this.balanceAfterMovement = balanceAfterMovement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public OffsetDateTime getMovementDate() {
        return movementDate;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalanceAfterMovement() {
        return balanceAfterMovement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movement)) return false;
        Movement movement = (Movement) o;
        return Objects.equals(accountId, movement.accountId) && Objects.equals(movementDate, movement.movementDate) && movementType == movement.movementType && Objects.equals(amount, movement.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, movementDate, movementType, amount);
    }
}
