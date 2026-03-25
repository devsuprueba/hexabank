package com.hexabank.client.infrastructure.controller;

import com.hexabank.client.domain.model.Cliente;
import com.hexabank.client.infrastructure.controller.dto.ClientRequestDTO;
import com.hexabank.client.infrastructure.controller.dto.ClientResponseDTO;

public final class ClienteMapper {

    private ClienteMapper() {}

    public static Cliente toDomain(ClientRequestDTO dto, Long id) {
        if (dto == null) {
            return null;
        }
        return new Cliente(id, dto.getNombre());
    }

    public static ClientResponseDTO toResponse(Cliente c) {
        if (c == null) {
            return null;
        }
        ClientResponseDTO r = new ClientResponseDTO();
        r.setId(c.getId());
        r.setNombre(c.getNombre());
        return r;
    }

}
