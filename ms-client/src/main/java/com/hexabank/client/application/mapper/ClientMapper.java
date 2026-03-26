package com.hexabank.client.application.mapper;

import com.hexabank.client.application.dto.ClientDto;
import com.hexabank.client.infra.persistence.ClientEntity;

public final class ClientMapper {

    private ClientMapper() {}

    public static ClientEntity toEntity(ClientDto dto) {
        if (dto == null) return null;
        ClientEntity e = new ClientEntity();
        e.setId(dto.getId());
        e.setName(dto.getName());
        e.setIdentification(dto.getIdentification());
        e.setGender(dto.getGender());
        e.setAge(dto.getAge());
        e.setAddress(dto.getAddress());
        e.setPhone(dto.getPhone());
        e.setStatus(dto.getStatus());
        return e;
    }

    public static ClientDto toDto(ClientEntity e) {
        if (e == null) return null;
        ClientDto dto = new ClientDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setIdentification(e.getIdentification());
        dto.setGender(e.getGender());
        dto.setAge(e.getAge());
        dto.setAddress(e.getAddress());
        dto.setPhone(e.getPhone());
        dto.setStatus(e.getStatus());
        return dto;
    }
}
