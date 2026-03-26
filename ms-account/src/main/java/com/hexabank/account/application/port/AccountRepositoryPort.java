package com.hexabank.account.application.port;

import com.hexabank.account.domain.entity.Account;

import java.util.Optional;
import java.util.List;

public interface AccountRepositoryPort {

    Account save(Account account);

    Optional<Account> findById(Long id);

    List<Account> findAll();
}
