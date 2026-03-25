package com.hexabank.client.infrastructure.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.hexabank.client.application.usecase.GetAllClientesUseCase;
import com.hexabank.client.domain.model.Cliente;
import com.hexabank.client.infrastructure.controller.dto.ClientResponseDTO;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private GetAllClientesUseCase getAllClientesUseCase;

    @InjectMocks
    private ReportService reportService;

    @Test
    void getClientsFiltersAndMaps() {
        Cliente a = new Cliente(1L, "Juan");
        Cliente b = new Cliente(2L, "Maria");
        when(getAllClientesUseCase.execute()).thenReturn(List.of(a, b));

        List<ClientResponseDTO> result = reportService.getClients("maria");

        assertEquals(1, result.size());
        ClientResponseDTO dto = result.get(0);
        assertEquals(2L, dto.getId());
        assertEquals("Maria", dto.getNombre());
    }

}
