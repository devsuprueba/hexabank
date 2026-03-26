package com.hexabank.account.application.usecase;

import com.hexabank.account.domain.entity.Account;

import java.math.BigDecimal;

public interface CreateAccountUseCase {

    Account create(Long clientId, String accountNumber, BigDecimal initialBalance);
}
