package com.hexabank.client.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Domain model for Cliente. Simple POJO used by the domain layer.
 * Note: persistence mapping is in infrastructure/entity package.
 */
@Getter
@Setter
public class Cliente {

    private Long id;
    private String nombre;

}
