package com.hexabank.account.application.service;

import com.hexabank.account.application.port.AccountRepositoryPort;
import com.hexabank.account.application.port.MovementRepositoryPort;
import com.hexabank.account.domain.entity.Account;
import com.hexabank.account.domain.entity.Movement;
import com.hexabank.account.domain.entity.Movement.MovementType;
import com.hexabank.account.domain.exception.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

class MovementServiceTest {

    private AccountRepositoryPort accountRepository;
    private MovementRepositoryPort movementRepository;
    private MovementService movementService;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepositoryPort.class);
        movementRepository = mock(MovementRepositoryPort.class);
        movementService = new MovementService(accountRepository, movementRepository);
    }

    @Test
    void depositShouldCreateMovementAndSaveAccount() {
        Account account = new Account(1L, "ACC-123", BigDecimal.valueOf(100));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(movementRepository.save(any(Movement.class))).thenAnswer(inv -> inv.getArgument(0));
        when(accountRepository.save(any(Account.class))).thenAnswer(inv -> inv.getArgument(0));

        Optional<Movement> result = movementService.register(1L, MovementType.DEPOSIT, BigDecimal.valueOf(50));

        assertTrue(result.isPresent());
        Movement m = result.get();
        assertEquals(MovementType.DEPOSIT, m.getMovementType());
        assertEquals(BigDecimal.valueOf(150), account.getCurrentBalance());
        verify(movementRepository).save(any(Movement.class));
        verify(accountRepository).save(account);
    }

    @Test
    void withdrawMoreThanBalanceShouldThrowDomainException() {
        Account account = new Account(2L, "ACC-456", BigDecimal.valueOf(20));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(account));

        assertThrows(DomainException.class,
            () -> movementService.register(2L, MovementType.WITHDRAWAL, BigDecimal.valueOf(50))
        );

        verify(movementRepository, never()).save(any());
        verify(accountRepository, never()).save(any());
    }
}
