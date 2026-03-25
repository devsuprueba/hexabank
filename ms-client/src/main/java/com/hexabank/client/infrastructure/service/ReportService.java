package com.hexabank.client.infrastructure.service;

import com.hexabank.client.application.usecase.GetAllClientesUseCase;
import com.hexabank.client.domain.model.Cliente;
import com.hexabank.client.infrastructure.controller.ClienteMapper;
import com.hexabank.client.infrastructure.controller.dto.ClientResponseDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private static final Logger log = LoggerFactory.getLogger(ReportService.class);

    private final GetAllClientesUseCase getAllClientesUseCase;

    public ReportService(GetAllClientesUseCase getAllClientesUseCase) {
        this.getAllClientesUseCase = getAllClientesUseCase;
    }

    public List<ClientResponseDTO> getClients(String nombre) {
        List<Cliente> all = getAllClientesUseCase.execute();
        List<ClientResponseDTO> filtered = all.stream()
                .filter(c -> matchesFilter(c, nombre))
                .map(ClienteMapper::toResponse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        log.info("Generated client list size={}", filtered.size());
        return filtered;
    }

    public StreamingResponseBody streamClientsExcel(String nombre) {
        List<Cliente> all = getAllClientesUseCase.execute();
        List<Cliente> filtered = all.stream()
                .filter(c -> matchesFilter(c, nombre))
                .collect(Collectors.toList());

        log.info("Preparing Excel for size={}", filtered.size());

        return out -> {
            SXSSFWorkbook workbook = new SXSSFWorkbook();
            try {
                Sheet sheet = workbook.createSheet("clients");
                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("Id");
                header.createCell(1).setCellValue("Nombre");

                int rowNum = 1;
                for (Cliente c : filtered) {
                    Row row = sheet.createRow(rowNum++);
                    if (c.getId() != null) {
                        row.createCell(0).setCellValue(c.getId());
                    } else {
                        row.createCell(0).setCellValue("");
                    }
                    row.createCell(1).setCellValue(c.getNombre() != null ? c.getNombre() : "");
                }

                workbook.write(out);
                out.flush();
            } finally {
                try {
                    workbook.close();
                } catch (Exception ignore) {
                    // ignore
                }
                workbook.dispose();
            }
        };
    }

    private boolean matchesFilter(Cliente c, String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return true;
        }
        return c.getNombre() != null && c.getNombre().toLowerCase().contains(nombre.toLowerCase());
    }

}
