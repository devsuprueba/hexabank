# Feature: JPA Configuration

## User Story
As a system,
I want to configure persistence,
so that data is stored efficiently and correctly.

---

## Tasks

### 1. Configure DataSource

Use PostgreSQL:

spring:
datasource:
url: jdbc:postgresql://postgres:5432/bank
username: user
password: pass

---

### 2. Configure JPA

spring:
jpa:
hibernate:
ddl-auto: none
show-sql: false

---

### 3. Entity Mapping

- Use @Entity in infrastructure layer
- Explicitly map columns

Example:

@Entity
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    private Long id;

    @Column(name = "nombre")
    private String nombre;
}

---

### 4. Native Queries

Use native queries when needed:

@Query(value = "SELECT * FROM cliente WHERE id = :id", nativeQuery = true)
Optional<ClienteEntity> findByIdNative(Long id);

---

## Rules

- Do not use default naming
- Avoid N+1 queries
- Use explicit mapping

---

## Acceptance Criteria

- Data persists correctly
- Queries return expected results