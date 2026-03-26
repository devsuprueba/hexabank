package com.hexabank.account.application.port;

import com.hexabank.account.domain.model.Account;

import java.util.Optional;

public interface AccountRepositoryPort {

    Account save(Account account);

    Optional<Account> findById(Long id);
}
