package com.hexabank.account.infra.controller;

import com.hexabank.account.application.usecase.GetReportUseCase;
import com.hexabank.account.infra.controller.dto.ReportResponse;
import com.hexabank.account.infra.report.ExcelReportGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportControllerTest {

    @Mock
    private GetReportUseCase reportUseCase;

    @Mock
    private ExcelReportGenerator excelGenerator;

    @InjectMocks
    private ReportController controller;

    @Test
    void jsonReportReturnsReportResponse() {
        final ReportResponse sample = new ReportResponse(null, List.of());
        when(reportUseCase.getReport(Optional.empty(), Optional.empty(), Optional.empty())).thenReturn(sample);

        final ReportResponse resp = controller.jsonReport(null, null, null);

        assertThat(resp).isSameAs(sample);
    }

    @Test
    void excelReportReturnsAttachment() {
        final ReportResponse sample = new ReportResponse(null, List.of());
        final byte[] bytes = new byte[]{1, 2, 3};
        when(reportUseCase.getReport(Optional.empty(), Optional.empty(), Optional.empty())).thenReturn(sample);
        when(excelGenerator.generate(sample)).thenReturn(bytes);

        final ResponseEntity<byte[]> resp = controller.excelReport(null, null, null);

        assertThat(resp.getHeaders().getFirst("Content-Disposition")).contains("attachment");
        assertThat(resp.getBody()).isEqualTo(bytes);
    }
}
