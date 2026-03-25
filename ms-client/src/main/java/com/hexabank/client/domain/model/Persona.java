package com.hexabank.client.domain.model;

/**
 * Pure domain representation of a Persona.
 * Immutable and validates required fields.
 */
public final class Persona {

    private final Long id;
    private final String nombre;

    public Persona(Long id, String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre requerido");
        }
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Persona{id=" + id + ", nombre='" + nombre + "'}";
    }

}
