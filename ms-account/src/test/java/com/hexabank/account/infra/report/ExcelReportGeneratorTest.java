package com.hexabank.account.infra.report;

import com.hexabank.account.infra.controller.dto.ReportResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ExcelReportGeneratorTest {

    @Test
    void generateProducesWorkbookBytes() throws Exception {
        final ReportResponse.MovementRowResponse row = new ReportResponse.MovementRowResponse(
                "2026-03-26T00:00:00Z", "DEPOSIT", BigDecimal.TEN, BigDecimal.TEN
        );

        final ReportResponse.AccountStatementResponse account = new ReportResponse.AccountStatementResponse(
                1L, "ACC-1", BigDecimal.TEN, List.of(row)
        );

        final ReportResponse resp = new ReportResponse(null, List.of(account));

        final ExcelReportGenerator gen = new ExcelReportGenerator();
        final byte[] bytes = gen.generate(resp);

        assertThat(bytes).isNotEmpty();

        try (var in = new ByteArrayInputStream(bytes); var wb = new XSSFWorkbook(in)) {
            assertThat(wb.getNumberOfSheets()).isGreaterThanOrEqualTo(1);
            final var sheet = wb.getSheetAt(0);
            assertThat(sheet.getRow(0).getCell(0).getStringCellValue()).isNotBlank();
        }
    }
}
