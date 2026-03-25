package com.hexabank.client.infrastructure.controller;

import com.hexabank.client.infrastructure.controller.dto.ClientResponseDTO;
import com.hexabank.client.infrastructure.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private static final Logger log = LoggerFactory.getLogger(ReportController.class);

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getReports(
            @RequestParam(value = "nombre", required = false) String nombre) {
        List<ClientResponseDTO> result = reportService.getClients(nombre);
        log.info("Returned JSON report size={}", result.size());
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/excel")
    public ResponseEntity<StreamingResponseBody> getExcel(
            @RequestParam(value = "nombre", required = false) String nombre) {
        StreamingResponseBody stream = reportService.streamClientsExcel(nombre);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clients-report.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(
                        MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                        )
                )
                .body(stream);
    }

}
