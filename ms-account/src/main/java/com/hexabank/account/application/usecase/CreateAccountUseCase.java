package com.hexabank.account.application.usecase;

import com.hexabank.account.domain.model.Account;

public interface CreateAccountUseCase {

    Account create(String ownerName, java.math.BigDecimal initialBalance);
}
