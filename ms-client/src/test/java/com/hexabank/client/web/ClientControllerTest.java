package com.hexabank.client.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexabank.client.application.dto.ClientDto;
import com.hexabank.client.infra.persistence.ClientEntity;
import com.hexabank.client.application.service.ClientService;
import com.hexabank.client.application.exception.DuplicateIdentificationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientControllerTest {

    private MockMvc mockMvc;
    private ClientService service;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String API_PATH = "/api/clients";

    @BeforeEach
    void setUp() {
        service = Mockito.mock(ClientService.class);
    org.springframework.validation.beanvalidation.LocalValidatorFactoryBean validator = new org.springframework.validation.beanvalidation.LocalValidatorFactoryBean();
    validator.afterPropertiesSet();
    mockMvc = MockMvcBuilders.standaloneSetup(new ClientController(service))
        .setControllerAdvice(new com.hexabank.client.infrastructure.controller.advice.GlobalExceptionHandler())
        .setValidator(validator)
        .build();
    }

    @Test
    void createWhenValidationFailsReturns400() throws Exception {
        ClientDto dto = new ClientDto();
        dto.setIdentification(""); // missing name and identification invalid

        mockMvc.perform(post(API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createWhenDuplicateReturns409() throws Exception {
        ClientDto dto = new ClientDto();
        dto.setName("Ana");
        dto.setIdentification("DUP1");

        when(service.create(any())).thenThrow(new DuplicateIdentificationException("identification already exists"));

        mockMvc.perform(post(API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict());
    }

    @Test
    void createWhenSuccessReturns201AndLocation() throws Exception {
        ClientDto dto = new ClientDto();
        dto.setName("Ana");
        dto.setIdentification("OK1");

        ClientEntity created = new ClientEntity();
        created.setId(10L);
        created.setName("Ana");
        created.setIdentification("OK1");

        when(service.create(any())).thenReturn(created);

    mockMvc.perform(post(API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }
}
