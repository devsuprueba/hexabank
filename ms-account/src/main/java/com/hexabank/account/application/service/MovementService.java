package com.hexabank.account.application.service;

import com.hexabank.account.application.port.AccountRepositoryPort;
import com.hexabank.account.application.port.MovementRepositoryPort;
import com.hexabank.account.application.usecase.GetMovementsUseCase;
import com.hexabank.account.application.usecase.RegisterMovementUseCase;
import com.hexabank.account.domain.entity.Account;
import com.hexabank.account.domain.entity.Movement;
import com.hexabank.account.domain.entity.Movement.MovementType;
import com.hexabank.account.domain.exception.DomainException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MovementService implements RegisterMovementUseCase, GetMovementsUseCase {

    private final AccountRepositoryPort accountRepository;
    private final MovementRepositoryPort movementRepository;

    public MovementService(AccountRepositoryPort accountRepository, MovementRepositoryPort movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    @Override
    @Transactional
    public Optional<Movement> register(Long accountId, MovementType type, BigDecimal amount) {
        Optional<Account> maybeAccount = accountRepository.findById(accountId);
        Account account = maybeAccount.orElseThrow(() -> new DomainException("Account not found"));
        Movement movement;
        if (type == MovementType.DEPOSIT) {
            movement = account.deposit(amount);
        } else if (type == MovementType.WITHDRAWAL) {
            movement = account.withdraw(amount);
        } else {
            throw new DomainException("Unsupported movement type");
        }
        Movement saved = movementRepository.save(movement);

        // domain's deposit/withdraw already updated account.currentBalance and created movement
        // persist the updated account state
        accountRepository.save(account);

        return Optional.of(saved);
    }

    @Override
    public List<Movement> getByAccountId(Long accountId) {
        return movementRepository.findByAccountId(accountId);
    }
}
