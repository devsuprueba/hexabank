# Feature: Domain Model

## Tasks

Create:

- Persona
- Cliente

---

## Rules

- No framework annotations
- No JPA
- No Spring
- No logging

---

## Business Rules

- Validate required fields
- Prevent invalid state

---

## Example

public class Cliente {

    private final Long id;
    private final String nombre;

    public Cliente(Long id, String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre requerido");
        }
        this.id = id;
        this.nombre = nombre;
    }
}

---

## Acceptance

- Domain is pure
- No external dependencies