package com.hexabank.account.application.service;

import com.hexabank.account.application.usecase.GetReportUseCase;
import com.hexabank.account.application.port.AccountRepositoryPort;
import com.hexabank.account.application.port.MovementRepositoryPort;
import com.hexabank.account.domain.entity.Account;
import com.hexabank.account.domain.entity.Movement;
import com.hexabank.account.infra.controller.dto.ReportResponse;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService implements GetReportUseCase {

    private final AccountRepositoryPort accountRepository;
    private final MovementRepositoryPort movementRepository;

    public ReportService(final AccountRepositoryPort accountRepository,
                         final MovementRepositoryPort movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    @Override
    public ReportResponse getReport(final Optional<Long> clientId,
                                    final Optional<OffsetDateTime> start,
                                    final Optional<OffsetDateTime> end) {
        final List<Account> accounts = accountRepository.findAll().stream()
                .filter(a -> clientId.map(id -> id.equals(a.getClientId())).orElse(true))
                .collect(Collectors.toList());

        final DateTimeFormatter df = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        final List<ReportResponse.AccountStatementResponse> accountStatements = accounts.stream()
                .map(a -> mapAccountToStatement(a, df, start, end))
                .collect(Collectors.toList());

        final Long cid = clientId.orElse(null);
        return new ReportResponse(cid, accountStatements);
    }

    private ReportResponse.AccountStatementResponse mapAccountToStatement(
            final Account account,
            final DateTimeFormatter df,
            final Optional<OffsetDateTime> start,
            final Optional<OffsetDateTime> end
    ) {
        final List<Movement> movements = movementRepository.findByAccountId(account.getId()).stream()
                .filter(m -> start.map(s -> !m.getMovementDate().isBefore(s)).orElse(true))
                .filter(m -> end.map(e -> !m.getMovementDate().isAfter(e)).orElse(true))
                .collect(Collectors.toList());

        final List<ReportResponse.MovementRowResponse> rows = movements.stream()
                .map(m -> new ReportResponse.MovementRowResponse(
                        m.getMovementDate().format(df),
                        m.getMovementType().name(),
                        m.getAmount(),
                        m.getBalanceAfterMovement()
                ))
                .collect(Collectors.toList());

        return new ReportResponse.AccountStatementResponse(
                account.getId(), account.getAccountNumber(), account.getCurrentBalance(), rows
        );
    }
}
