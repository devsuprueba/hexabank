package com.hexabank.account.infra.controller;

import com.hexabank.account.application.usecase.GetReportUseCase;
import com.hexabank.account.infra.report.ExcelReportGenerator;
import com.hexabank.account.infra.controller.dto.ReportResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReportControllerMvcTest {

    private MockMvc mockMvc;

    @Mock
    private GetReportUseCase reportUseCase;

    @Mock
    private ExcelReportGenerator excelGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        final ReportController controller = new ReportController(reportUseCase, excelGenerator);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void jsonEndpointShouldReturnOkAndJson() throws Exception {
        when(reportUseCase.getReport(Optional.empty(), Optional.empty(), Optional.empty()))
                .thenReturn(new ReportResponse(null, List.of()));

        mockMvc.perform(get("/reports").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void excelEndpointShouldReturnAttachment() throws Exception {
        final byte[] payload = new byte[]{1, 2, 3};
        when(reportUseCase.getReport(Optional.empty(), Optional.empty(), Optional.empty()))
                .thenReturn(new ReportResponse(null, List.of()));
        when(excelGenerator.generate(any(ReportResponse.class))).thenReturn(payload);

        mockMvc.perform(
                get("/reports/excel")
                        .accept("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
        ).andExpect(status().isOk())
                .andExpect(header().string(
                        "Content-Disposition",
                        org.hamcrest.Matchers.containsString("attachment")
                ))
                .andExpect(content().bytes(payload));
    }
}
