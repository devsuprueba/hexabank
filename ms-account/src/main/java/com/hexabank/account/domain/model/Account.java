package com.hexabank.account.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "account_number", length = 50)
    private String accountNumber;

    @Column(name = "account_type", length = 50)
    private String accountType;

    @Column(name = "owner_name", nullable = false, length = 200)
    private String ownerName;

    @Column(name = "initial_balance", nullable = false)
    private BigDecimal initialBalance = BigDecimal.ZERO;

    @Column(name = "current_balance", nullable = false)
    private BigDecimal currentBalance = BigDecimal.ZERO;

    @Column(name = "status", length = 50)
    private String status;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    protected Account() {
        // JPA
    }

    public Account(String ownerName, BigDecimal initialBalance) {
        this.ownerName = ownerName;
        this.initialBalance = initialBalance == null ? BigDecimal.ZERO : initialBalance;
        this.currentBalance = this.initialBalance;
        this.status = "ACTIVE";
    }

    public Long getId() {
        return id;
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

    public String getOwnerName() {
        return ownerName;
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

    public Integer getVersion() {
        return version;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCurrentBalance(BigDecimal newBalance) {
        this.currentBalance = newBalance;
    }
}
