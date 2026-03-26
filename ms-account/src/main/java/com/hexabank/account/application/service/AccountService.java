package com.hexabank.account.application.service;

import com.hexabank.account.application.port.AccountRepositoryPort;
import com.hexabank.account.application.usecase.CreateAccountUseCase;
import com.hexabank.account.domain.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService implements CreateAccountUseCase {

    private final AccountRepositoryPort repository;

    public AccountService(AccountRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Account create(String ownerName, BigDecimal initialBalance) {
        Account account = new Account(ownerName, initialBalance);
        return repository.save(account);
    }
}
