package com.hexabank.account.application.service;

import com.hexabank.account.application.port.AccountRepositoryPort;
import com.hexabank.account.application.usecase.GetAccountUseCase;
import com.hexabank.account.application.usecase.GetAccountsUseCase;
import com.hexabank.account.domain.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountQueryService implements GetAccountUseCase, GetAccountsUseCase {

    private final AccountRepositoryPort repository;

    public AccountQueryService(AccountRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Account> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Account> getAll() {
        // simple non-paginated retrieval for now
        return repository.findAll();
    }
}
