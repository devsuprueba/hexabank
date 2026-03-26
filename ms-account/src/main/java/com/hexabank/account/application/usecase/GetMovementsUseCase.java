package com.hexabank.account.application.usecase;

import com.hexabank.account.domain.entity.Movement;

import java.util.List;

public interface GetMovementsUseCase {

    List<Movement> getByAccountId(Long accountId);
}
