package com.hexabank.account.infra.adapter;

import com.hexabank.account.application.port.AccountRepositoryPort;
import com.hexabank.account.domain.model.Account;
import com.hexabank.account.infra.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaAccountRepositoryAdapter implements AccountRepositoryPort {

    private final AccountRepository repository;

    public JpaAccountRepositoryAdapter(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account save(Account account) {
        return repository.save(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id);
    }
}
