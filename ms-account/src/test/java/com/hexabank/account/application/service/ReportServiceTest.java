package com.hexabank.account.application.service;

import com.hexabank.account.application.port.AccountRepositoryPort;
import com.hexabank.account.application.port.MovementRepositoryPort;
import com.hexabank.account.domain.entity.Account;
import com.hexabank.account.domain.entity.Movement;
import com.hexabank.account.infra.controller.dto.ReportResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private AccountRepositoryPort accountRepository;

    @Mock
    private MovementRepositoryPort movementRepository;

    @InjectMocks
    private ReportService reportService;

    private Account account;
    private Movement movement;

    @BeforeEach
    void setUp() {
        account = new Account(10L, "ACC-100", BigDecimal.ZERO);
        account.setId(100L);
        movement = account.deposit(BigDecimal.valueOf(50));
        // ensure movement has account id set
        movement.setId(1L);
    }

    @Test
    void getReportFiltersByClientId() {
        when(accountRepository.findAll()).thenReturn(List.of(account));
        when(movementRepository.findByAccountId(anyLong())).thenReturn(List.of(movement));

        final ReportResponse resp = reportService.getReport(Optional.of(10L), Optional.empty(), Optional.empty());

        assertThat(resp).isNotNull();
        assertThat(resp.getClientId()).isEqualTo(10L);
        assertThat(resp.getAccounts()).hasSize(1);

        final ReportResponse.AccountStatementResponse stmt = resp.getAccounts().get(0);
        assertThat(stmt.getAccountId()).isEqualTo(100L);
        assertThat(stmt.getMovements()).hasSize(1);
        final ReportResponse.MovementRowResponse row = stmt.getMovements().get(0);
        assertThat(row.getMovementType()).isEqualTo(movement.getMovementType().name());
        assertThat(row.getAmount()).isEqualTo(movement.getAmount());
    }
}
