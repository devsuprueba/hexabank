package com.hexabank.client.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class ClienteEntity {

    @Id
    private Long id;

    @Column(name = "nombre")
    private String nombre;

}
