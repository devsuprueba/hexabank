package com.hexabank.account.infra.adapter;

import com.hexabank.account.application.port.AccountRepositoryPort;
import com.hexabank.account.domain.entity.Account;
import com.hexabank.account.infra.AccountRepository;
import com.hexabank.account.infra.mapper.AccountPersistenceMapper;
import com.hexabank.account.infra.persistence.AccountEntity;
import java.util.List;
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
        AccountEntity entity = AccountPersistenceMapper.toEntity(account);
        AccountEntity saved = repository.save(entity);
        return AccountPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repository.findById(id).map(AccountPersistenceMapper::toDomain);
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll().stream().map(AccountPersistenceMapper::toDomain).toList();
    }
}
