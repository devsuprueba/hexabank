package com.hexabank.account.infra.report;

import com.hexabank.account.infra.controller.dto.ReportResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class ExcelReportGenerator {

    public byte[] generate(final ReportResponse report) {
        try (final Workbook wb = new XSSFWorkbook(); final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            final Sheet sheet = wb.createSheet("Report");
            int rownum = 0;

            writeHeader(sheet, rownum++);

            final List<ReportResponse.AccountStatementResponse> accounts = report.getAccounts();
            if (accounts != null) {
                for (final ReportResponse.AccountStatementResponse acc : accounts) {
                    rownum = writeAccountRows(sheet, acc, rownum);
                }
            }

            // autosize columns for readability
            for (int i = 0; i <= 6; i++) {
                sheet.autoSizeColumn(i);
            }

            wb.write(out);
            return out.toByteArray();
        } catch (final IOException e) {
            throw new IllegalStateException("Failed to build Excel report", e);
        }
    }

    private void writeHeader(final Sheet sheet, final int rownum) {
        final Row header = sheet.createRow(rownum);
        header.createCell(0).setCellValue("AccountId");
        header.createCell(1).setCellValue("AccountNumber");
        header.createCell(2).setCellValue("CurrentBalance");
        header.createCell(3).setCellValue("MovementDate");
        header.createCell(4).setCellValue("MovementType");
        header.createCell(5).setCellValue("Amount");
        header.createCell(6).setCellValue("BalanceAfter");
    }

    private int writeAccountRows(final Sheet sheet, final ReportResponse.AccountStatementResponse acc, int rownum) {
        final List<ReportResponse.MovementRowResponse> movements = acc.getMovements();
        if (movements == null || movements.isEmpty()) {
            final Row r = sheet.createRow(rownum++);
            createCell(r, 0, String.valueOf(acc.getAccountId()));
            createCell(r, 1, acc.getAccountNumber());
            createCell(r, 2, acc.getCurrentBalance() == null ? "" : acc.getCurrentBalance().toString());
            return rownum;
        }

        for (final ReportResponse.MovementRowResponse mv : movements) {
            final Row r = sheet.createRow(rownum++);
            createCell(r, 0, String.valueOf(acc.getAccountId()));
            createCell(r, 1, acc.getAccountNumber());
            createCell(r, 2, acc.getCurrentBalance() == null ? "" : acc.getCurrentBalance().toString());
            createCell(r, 3, mv.getMovementDate());
            createCell(r, 4, mv.getMovementType());
            createCell(r, 5, mv.getAmount() == null ? "" : mv.getAmount().toString());
            createCell(r, 6, mv.getBalanceAfter() == null ? "" : mv.getBalanceAfter().toString());
        }

        return rownum;
    }

    private void createCell(final Row row, final int col, final String value) {
        final Cell c = row.createCell(col);
        c.setCellValue(value == null ? "" : value);
    }
}
