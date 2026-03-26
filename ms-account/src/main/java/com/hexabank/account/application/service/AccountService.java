package com.hexabank.account.application.service;

import com.hexabank.account.application.port.AccountRepositoryPort;
import com.hexabank.account.application.usecase.CreateAccountUseCase;
import com.hexabank.account.domain.entity.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService implements CreateAccountUseCase {

    private final AccountRepositoryPort repository;

    public AccountService(AccountRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Account create(Long clientId, String accountNumber, BigDecimal initialBalance) {
        Account account = new Account(clientId, accountNumber, initialBalance);
        return repository.save(account);
    }
}
