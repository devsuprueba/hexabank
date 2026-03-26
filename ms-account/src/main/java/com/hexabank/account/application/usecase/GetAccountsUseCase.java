package com.hexabank.account.application.usecase;

import com.hexabank.account.domain.entity.Account;

import java.util.List;

public interface GetAccountsUseCase {

    List<Account> getAll();
}
