# F24 — API de Clientes (ms-client)

## Propósito

Definir la API REST para el microservicio `ms-client` que expone operaciones CRUD sobre la entidad Client. Este documento sirve como especificación funcional y de aceptación (endpoints, cuerpos de petición/respuesta, validaciones, criterios de aceptación y pruebas).

## Contexto

`ms-client` es responsable por la gestión del catálogo de clientes. Otros microservicios (por ejemplo `ms-account`) pueden requerir referencias a clientes (identificación única). La implementación debe seguir las convenciones del proyecto: Java 21, Spring Boot 4, Spring Data JPA, Checkstyle vigente, y Flyway para migraciones.

## Contrato (inputs/outputs)

- Base URL: configurable por variable de entorno/`application.properties` (por defecto en desarrollo: `http://localhost:8081`).
- Recursos principales:
  - POST /api/clients — crear un cliente
    - Input: JSON con los campos del DTO (véase abajo)
    - Output: 201 CREATED, Location header con `/api/clients/{id}` y body con el cliente creado (incluye `id`).
  - GET /api/clients — listar clientes
    - Output: 200 OK, array de clientes (paginar en F26 si se requiere más adelante).
  - GET /api/clients/{id} — obtener cliente por id
    - Output: 200 OK + cliente | 404 Not Found si no existe.
  - PUT /api/clients/{id} — actualizar cliente
    - Input: JSON con campos a actualizar (puede ser el DTO completo)
    - Output: 200 OK + cliente actualizado | 404 Not Found.
  - DELETE /api/clients/{id} — eliminar cliente
    - Output: 204 No Content (idempotente; 404 si no existe y se elige ese comportamiento).

## DTO (ClientDto)

- id: Long (salida)
- name: String (obligatorio, max 200)
- identification: String (obligatorio, único, max 100)
- gender: String (opcional, max 20)
- age: Integer (opcional, >=0)
- address: String (opcional, max 300)
- phone: String (opcional, max 50)
- status: Boolean (opcional, default true)

## Validaciones

- `name` y `identification` son obligatorios al crear.
- `identification` debe ser única (constraint DB + comprobación en servicio para devolver 409 Conflict si ya existe).
- `age` debe ser >= 0 cuando se entregue.
- usar `@Valid` y anotaciones JSR-380 en DTOs para validación automática en controladores.

## Persistencia y migración

- Se debe crear una migración Flyway en `ms-client/src/main/resources/db/migration/` con nombre `V1__create_client_table.sql` (o el número siguiente acorde al módulo) que cree la tabla `client` con las columnas definidas en `ClientEntity` y un índice único sobre `identification`.

## Ejemplo de DDL (Flyway)

-- V1\_\_create_client_table.sql
-- CREATE TABLE client (
-- id BIGSERIAL PRIMARY KEY,
-- name VARCHAR(200) NOT NULL,
-- identification VARCHAR(100) NOT NULL UNIQUE,
-- gender VARCHAR(20),
-- age INTEGER,
-- address VARCHAR(300),
-- phone VARCHAR(50),
-- status BOOLEAN DEFAULT true
-- );

## Errores y códigos HTTP

- 400 Bad Request: payload inválido o violación de validaciones JSR-380.
- 404 Not Found: recurso no encontrado.
- 409 Conflict: intento de crear/actualizar con `identification` duplicada.
- 500 Internal Server Error: errores inesperados (loguear y retornar mensaje genérico).

## Pruebas y criterios de aceptación

Automatizar pruebas unitarias e integración:

- Unitarias (JUnit + Mockito):
  - `ClientServiceTest`: cubrir happy path de create/get/update/delete y casos de error (not found, duplicate identification).
  - `ClientControllerTest`: MockMvc con controlador aislado; validar respuestas HTTP y headers (Location en create).

- Integración (DataJpa / @SpringBootTest slice):
  - `ClientRepositoryIT`: usar DB embebida/H2 con Flyway migración aplicada; validar persistencia y constraint unique sobre `identification`.
  - `ClientControllerIT`: levantar contexto web (WebEnvironment.RANDOM_PORT) y verificar flujo create->get->update->delete.

Criterios de aceptación (mínimos):

1. Crear cliente válido devuelve 201 y el cliente es persistido (GET por id lo devuelve).
2. Intentar crear con `identification` existente devuelve 409.
3. Actualizar campos devuelve 200 y refleja los cambios.
4. Borrar un cliente elimina el registro (GET devuelve 404).

## Postman / Colección

- Se incluirá una colección Postman con las operaciones CRUD y un environment con `baseUrl` (ya provisto en `ms-client/docs/postman`). Se debe documentar el flujo (crear -> listar -> obtener -> actualizar -> eliminar).

## Consideraciones Cross-service

- `ms-account` puede requerir sólo la referencia `identification` o `id` del cliente. Decidir en la integración si se expone un endpoint adicional para buscar por `identification` (por ejemplo GET /api/clients/by-identification/{identification}).

## Seguridad

- Por ahora la API es abierta en entorno de desarrollo. En entornos staging/prod debe protegerse con el mecanismo de autenticación y autorización elegido por la organización (JWT/OAuth2) y documentarse en README.

## Observabilidad y métricas

- Exponer Actuator (health, metrics, prometheus) heredado de la configuración del proyecto.
- Agregar métricas custom opcionales: contador de creaciones/actualizaciones/errores de clientes.

## Notas de implementación

- Seguir las convenciones del repositorio para paquetes (domain, infra/persistence, application/service, web).
- Evitar constructores con >7 parámetros (usar DTO/holder si es necesario) para cumplir Checkstyle.
- Manejar transacciones en el servicio cuando actualice entidades.

## Aceptación final

Merge request/commit se considera aceptado cuando:

- Todos los tests unitarios y de integración pasan (`./gradlew test`).
- Checkstyle pasa sin errores.
- Se añadió la migración Flyway y la colección Postman.
- Documentación básica (README o docs/ai-context) referenciando la API y cómo probarla localmente.

---

Fecha: 26-03-2026
Autor: equipo de desarrollo (generado automáticamente)

# Feature: Client API

## Tasks

Endpoints:

- POST /clients
- GET /clients
- PUT /clients/{id}
- DELETE /clients/{id}

---

## Acceptance Criteria

- API works
