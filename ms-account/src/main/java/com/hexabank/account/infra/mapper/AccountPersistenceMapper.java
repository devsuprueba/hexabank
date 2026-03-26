package com.hexabank.account.infra.mapper;

import com.hexabank.account.domain.entity.Account;
import com.hexabank.account.domain.entity.Movement;
import com.hexabank.account.infra.persistence.AccountEntity;
import com.hexabank.account.infra.persistence.MovementEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class AccountPersistenceMapper {

    private AccountPersistenceMapper() {
    }

    public static Account toDomain(AccountEntity e) {
        if (e == null) {
            return null;
        }

        Long clientId = e.getClientId() == null ? 0L : e.getClientId();
        String accountNumber = e.getAccountNumber() == null ? "" : e.getAccountNumber();
        Account a = new Account(clientId, accountNumber, e.getInitialBalance());
        a.setId(e.getId());
        a.setOwnerName(e.getOwnerName());
        a.setAccountType(e.getAccountType());
        a.setStatus(e.getStatus());
        a.setAccountType(e.getAccountType());
        // map movements if present
        // movements mapping is optional here; for now we don't set movement ids into domain movements
        return a;
    }

    public static AccountEntity toEntity(Account domain) {
        if (domain == null) {
            return null;
        }

        AccountEntity e = new AccountEntity();
        e.setId(domain.getId());
        e.setClientId(domain.getClientId());
        e.setAccountNumber(domain.getAccountNumber());
        e.setAccountType(domain.getAccountType());
        // map ownerName from domain if present
        e.setOwnerName(domain.getOwnerName());
        e.setInitialBalance(domain.getInitialBalance());
        e.setCurrentBalance(domain.getCurrentBalance());
        e.setStatus(domain.getStatus());
        e.setCreatedAt(domain.getCreatedAt());
        e.setUpdatedAt(domain.getUpdatedAt());
        return e;
    }

    public static Movement toDomainMovement(MovementEntity me) {
        if (me == null) {
            return null;
        }

        Movement.MovementType type = Movement.MovementType.valueOf(me.getMovementType());
        Movement m = new Movement(
                me.getAccountId(),
                me.getMovementDate(),
                type,
                me.getAmount(),
                me.getBalanceAfterMovement()
        );
        m.setId(me.getId());
        return m;
    }

    public static MovementEntity toEntityMovement(Movement m) {
        if (m == null) {
            return null;
        }

        MovementEntity me = new MovementEntity();
        me.setId(m.getId());
        me.setAccountId(m.getAccountId());
        me.setMovementDate(m.getMovementDate());
        me.setMovementType(m.getMovementType().name());
        me.setAmount(m.getAmount());
        me.setBalanceAfterMovement(m.getBalanceAfterMovement());
        return me;
    }
}
