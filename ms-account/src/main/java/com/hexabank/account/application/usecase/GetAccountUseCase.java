package com.hexabank.account.application.usecase;

import com.hexabank.account.domain.entity.Account;

import java.util.Optional;

public interface GetAccountUseCase {

    Optional<Account> getById(Long id);
}
