package com.hexabank.account.domain.entity;

import com.hexabank.account.domain.exception.DomainException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Framework-independent domain model for Account.
 * Encapsulates business rules and invariants.
 */
public class Account {

    private Long id;
    private final Long clientId;
    private final String accountNumber;
    private String ownerName;
    private String accountType;
    private final BigDecimal initialBalance;
    private BigDecimal currentBalance;
    private String status;
    private final List<Movement> movements = new ArrayList<>();
    private final OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Account(Long clientId, String accountNumber, BigDecimal initialBalance) {
        if (clientId == null || clientId <= 0) {
            throw new DomainException("clientId must be provided and positive");
        }

        if (accountNumber == null || accountNumber.isBlank()) {
            throw new DomainException("accountNumber is mandatory");
        }
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.initialBalance = initialBalance == null ? BigDecimal.ZERO : initialBalance;
        this.currentBalance = this.initialBalance;
        this.status = "ACTIVE";
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = this.createdAt;
    }

    /**
     * Optional ownerName setter/getter for persistence and DTO mapping.
     */
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /* --- Business operations --- */
    public Movement deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("deposit amount must be positive");
        }
        this.currentBalance = this.currentBalance.add(amount);
        this.updatedAt = OffsetDateTime.now();
        Movement m = new Movement(
                this.id,
                this.updatedAt,
                Movement.MovementType.DEPOSIT,
                amount,
                this.currentBalance
        );
        this.movements.add(m);
        return m;
    }

    public Movement withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException("withdraw amount must be positive");
        }

        if (this.currentBalance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainException("withdrawal would leave account with negative balance");
        }

        this.currentBalance = this.currentBalance.subtract(amount);
        this.updatedAt = OffsetDateTime.now();
        // For withdrawals we store negative amount to follow spec guidance
        Movement m = new Movement(
                this.id,
                this.updatedAt,
                Movement.MovementType.WITHDRAWAL,
                amount.negate(),
                this.currentBalance
        );
        this.movements.add(m);
        return m;
    }

    /* --- Getters / helpers --- */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Movement> getMovements() {
        return Collections.unmodifiableList(movements);
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Account)) {
            return false;
        }

        Account account = (Account) o;
        return Objects.equals(clientId, account.clientId)
                && Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, accountNumber);
    }
}
