# ms-client

Microservicio responsable por la gestión del catálogo de clientes para Hexabank.

## Contenido

- API REST para CRUD de clientes
- Persistencia: Spring Data JPA + Flyway migrations
- Observability: Actuator + Prometheus (heredado de la configuración del proyecto)
- Postman collection incluida en `docs/postman`

## Requisitos

- Java 21
- Gradle (usar el wrapper `./gradlew`)
- Base de datos PostgreSQL (o H2 en pruebas)

## Ejecutar localmente

1. Configura la base de datos (usar docker-compose o tu PG local). Por ejemplo `docker-compose.yml` del workspace.
2. Ejecuta la aplicación en el puerto 8081 (por defecto):

```bash
cd ms-client
./gradlew bootRun
```

## Variables de entorno relevantes

- `SPRING_DATASOURCE_URL` - JDBC URL de la base de datos
- `SPRING_DATASOURCE_USERNAME` - DB user
- `SPRING_DATASOURCE_PASSWORD` - DB password
- `SERVER_PORT` - puerto (por defecto 8081)

## Migraciones (Flyway)

Las migraciones están en `src/main/resources/db/migration`. Asegúrate de que la base de datos esté accesible antes de iniciar la app; Flyway aplicará los scripts al arrancar.

## Postman

Colección disponible en: `docs/postman/Hexabank-ms-client.postman_collection.json`.
Environment: `docs/postman/Hexabank-local-ms-client.postman_environment.json` (por defecto `baseUrl=http://localhost:8081`).

## API (resumen)

- POST /api/clients — crear cliente
  - 201 Created, Location header → /api/clients/{id}
- GET /api/clients — listar
- GET /api/clients/{id} — obtener por id
- PUT /api/clients/{id} — actualizar
- DELETE /api/clients/{id} — eliminar

## Validaciones y errores

- `name` y `identification` son obligatorios al crear.
- `identification` es único; intento de crear/actualizar con duplicado devuelve 409 Conflict.
- Validaciones de DTO usan JSR-380 (`@Valid` en controladores).

## Tests

Ejecutar tests:

```bash
cd ms-client
./gradlew test
```

Pruebas a añadir/robustecer (sugerido)

- `ClientServiceTest` — cover happy path + duplicate id and not found cases
- `ClientControllerTest` — MockMvc tests for 201/400/404/409 responses
- `ClientRepositoryIT` — DataJpa test verifying Flyway migration and unique constraint

## Notas

- Evitar constructores con más de 7 parámetros (Checkstyle). Usar DTOs o builders si es necesario.
- Para integración con `ms-account`, considerar exponer búsqueda por `identification` si se requiere.

## Contacto

# ms-client

Microservicio responsable por la gestión del catálogo de clientes para Hexabank.

## Contenido

- API REST para CRUD de clientes
- Persistencia: Spring Data JPA + Flyway migrations
- Observability: Actuator + Prometheus (heredado de la configuración del proyecto)
- Postman collection incluida en `docs/postman`

## Requisitos

- Java 21
- Gradle (usar el wrapper `./gradlew`)
- Base de datos PostgreSQL (o H2 en pruebas)

## Ejecutar localmente

1. Configura la base de datos (usar docker-compose o tu PG local). Por ejemplo `docker-compose.yml` del workspace.
2. Ejecuta la aplicación en el puerto 8081 (por defecto):

```bash
cd ms-client
./gradlew bootRun
```

### Ejecutar con H2 (modo rápido)

Para pruebas locales sin Postgres:

```bash
./gradlew bootRun -Dspring.profiles.active=local
```

o ejecutando el jar con H2 embebida:

```bash
./gradlew bootJar
java -Dspring.datasource.url=jdbc:h2:mem:testdb -Dspring.datasource.driver-class-name=org.h2.Driver -jar build/libs/*.jar
```

## Variables de entorno relevantes

- `SPRING_DATASOURCE_URL` - JDBC URL de la base de datos
- `SPRING_DATASOURCE_USERNAME` - DB user
- `SPRING_DATASOURCE_PASSWORD` - DB password
- `SERVER_PORT` - puerto (por defecto 8081)

## Migraciones (Flyway)

Las migraciones están en `src/main/resources/db/migration`. La migración que crea la tabla `client` es:

`V4__create_client_table.sql` (aplicable automáticamente al arrancar).

## Docker / docker-compose (ejemplo)

Snippet mínimo `docker-compose` para levantar Postgres y la app (ajustar según tu entorno):

```yaml
version: "3.8"
services:
  db:
    image: postgres:15
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: hexabank
    ports:
      - "5432:5432"

  ms-client:
    build: .
    depends_on:
      - db
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/hexabank
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: postgres
    ports:
      - "8081:8081"
```

## Postman

Colección disponible en: `docs/postman/Hexabank-ms-client.postman_collection.json`.
Environment: `docs/postman/Hexabank-local-ms-client.postman_environment.json` (por defecto `baseUrl=http://localhost:8081`).

### Importar y probar

1. Importa la colección y el environment en Postman.
2. Selecciona el environment `Hexabank - Local ms-client` y ejecuta las peticiones en el orden: Create -> List -> Get by id -> Update -> Delete.

## API (resumen)

- POST /api/clients — crear cliente
  - 201 Created, Location header → /api/clients/{id}
- GET /api/clients — listar
- GET /api/clients/{id} — obtener por id
- PUT /api/clients/{id} — actualizar
- DELETE /api/clients/{id} — eliminar

### Ejemplos curl

Crear un cliente:

```bash
curl -X POST '{{baseUrl}}/api/clients' \
  -H 'Content-Type: application/json' \
  -d '{"name":"María López","identification":"98765432","gender":"F","age":29,"address":"Av. Central 123","phone":"+56911223344","status":true}'
```

Listar clientes:

```bash
curl '{{baseUrl}}/api/clients'
```

Obtener por id:

```bash
curl '{{baseUrl}}/api/clients/1'
```

Actualizar:

```bash
curl -X PUT '{{baseUrl}}/api/clients/1' -H 'Content-Type: application/json' -d '{"name":"María L.","identification":"98765432"}'
```

Borrar:

```bash
curl -X DELETE '{{baseUrl}}/api/clients/1'
```

## Validaciones y errores

- `name` y `identification` son obligatorios al crear.
- `identification` es único; intento de crear/actualizar con duplicado devuelve 409 Conflict.
- Validaciones de DTO usan JSR-380 (`@Valid` en controladores). Los errores de validación devuelven 400 con detalle por campo.

## Observability

- Actuator endpoints disponibles: `/actuator/health`, `/actuator/metrics`, `/actuator/prometheus`.

## Tests

Ejecutar tests:

```bash
cd ms-client
./gradlew test
```

Pruebas recomendadas a añadir/robustecer

- `ClientServiceTest` — cover happy path + duplicate id and not found cases
- `ClientControllerTest` — MockMvc tests for 201/400/404/409 responses
- `ClientRepositoryIT` — DataJpa test verifying Flyway migration and unique constraint

## Notas

- Evitar constructores con más de 7 parámetros (Checkstyle). Usar DTOs o builders si es necesario.
- Para integración con `ms-account`, considerar exponer búsqueda por `identification` si se requiere.
- No almacenar secretos en texto plano en manifest o repositorios; usar variables de entorno o Kubernetes Secrets en despliegues.

## Contacto

Equipo de desarrollo Hexabank.
