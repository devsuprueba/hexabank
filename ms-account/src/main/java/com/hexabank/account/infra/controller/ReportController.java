package com.hexabank.account.infra.controller;

import com.hexabank.account.application.usecase.GetReportUseCase;
import com.hexabank.account.infra.controller.dto.ReportResponse;
import com.hexabank.account.infra.report.ExcelReportGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final GetReportUseCase reportService;
    private final ExcelReportGenerator excelGenerator;

    public ReportController(final GetReportUseCase reportService, final ExcelReportGenerator excelGenerator) {
        this.reportService = reportService;
        this.excelGenerator = excelGenerator;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ReportResponse jsonReport(
            @RequestParam(name = "clientId", required = false) final Long clientId,
            @RequestParam(name = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final OffsetDateTime startDate,
            @RequestParam(name = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final OffsetDateTime endDate
    ) {
        return reportService.getReport(
                Optional.ofNullable(clientId), Optional.ofNullable(startDate), Optional.ofNullable(endDate)
        );
    }

    @GetMapping(path = "/excel")
    public ResponseEntity<byte[]> excelReport(
            @RequestParam(name = "clientId", required = false) final Long clientId,
            @RequestParam(name = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final OffsetDateTime startDate,
            @RequestParam(name = "endDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final OffsetDateTime endDate
    ) {
        final ReportResponse resp = reportService.getReport(
                Optional.ofNullable(clientId), Optional.ofNullable(startDate), Optional.ofNullable(endDate)
        );

        final byte[] content = excelGenerator.generate(resp);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xlsx")
                .contentType(
                        MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                        )
                )
                .body(content);
    }
}
