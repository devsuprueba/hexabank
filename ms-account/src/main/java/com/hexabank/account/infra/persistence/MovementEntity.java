package com.hexabank.account.infra.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "movement")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "movement_date")
    private OffsetDateTime movementDate;

    @Column(name = "movement_type", length = 50)
    private String movementType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "balance_after_movement")
    private BigDecimal balanceAfterMovement;

    public MovementEntity() {
        // JPA
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

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public OffsetDateTime getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(OffsetDateTime movementDate) {
        this.movementDate = movementDate;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalanceAfterMovement() {
        return balanceAfterMovement;
    }

    public void setBalanceAfterMovement(BigDecimal balanceAfterMovement) {
        this.balanceAfterMovement = balanceAfterMovement;
    }
}
