package com.hexabank.account.application.usecase;

import com.hexabank.account.infra.controller.dto.ReportResponse;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface GetReportUseCase {

    /**
     * Build an account report optionally filtered by clientId and date range.
     */
    ReportResponse getReport(Optional<Long> clientId, Optional<OffsetDateTime> start, Optional<OffsetDateTime> end);
}
