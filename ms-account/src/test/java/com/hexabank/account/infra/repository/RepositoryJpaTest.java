package com.hexabank.account.infra.repository;

import com.hexabank.account.infra.AccountRepository;
import com.hexabank.account.infra.MovementRepository;
import com.hexabank.account.infra.persistence.AccountEntity;
import com.hexabank.account.infra.persistence.MovementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

class RepositoryJpaTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MovementRepository movementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void repositoryMocksCanBeUsed() {
        final AccountEntity acc = new AccountEntity();
        acc.setClientId(55L);
        acc.setAccountNumber("ACC-55");
        acc.setOwnerName("Owner");

        // verify that mocks accept save calls without starting Spring context
        accountRepository.save(acc);

        final MovementEntity m = new MovementEntity();
        m.setAccountId(1L);
        m.setMovementType("DEPOSIT");
        m.setAmount(BigDecimal.TEN);
        movementRepository.save(m);
    }
}
