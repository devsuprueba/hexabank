# Hexabank — Infraestructura (resumen)

Este documento reúne las instrucciones y enlaces principales para levantar y validar la infraestructura mínima del proyecto Hexabank (microservicios `ms-client` y `ms-account`) en entorno local y en Kubernetes.

Usa los documentos F01..F03 para la especificación detallada:

- F01 — Infrastructure Docker Compose: `docs/ai-context/infraestructure/F01-infrastructure-docker-compose.md`
- F02 — System Architecture: `docs/ai-context/infraestructure/F02-system-architecture.md`
- F03 — Environment Config: `docs/ai-context/infraestructure/F03-environment-config.md`

## Quick start (local, Docker Compose)

Prerequisitos:

- Docker y Docker Compose instalados
- Java/Gradle solo si quieres ejecutar servicios desde el código

1. Copia el ejemplo de variables y completa tus valores locales:

```bash
cp .env.example .env
# editar .env y rellenar secretos
```

2. Levanta la infraestructura mínima (Postgres, Zookeeper, Kafka):

```bash
docker-compose up -d postgres zookeeper kafka
```

3. Crea topics idempotentes (helper `kafka-init` incluido en `docker-compose.yml`):

```bash
docker-compose up --no-deps --force-recreate kafka-init
docker-compose logs kafka-init
```

4. Levanta los microservicios:

```bash
docker-compose up --build ms-client ms-account
```

5. Validaciones rápidas:

```bash
curl -sS http://localhost:8081/actuator/health | jq .
curl -sS http://localhost:8082/actuator/health | jq .
docker-compose exec kafka /opt/bitnami/kafka/bin/kafka-topics.sh --list --bootstrap-server kafka:9092
# buscar: account-created, movement-created
```

## Run services from source (dev)

Para ejecutar un servicio individual desde el código (útil para desarrollo):

```bash
cd ms-client
./gradlew bootRun

# en otro terminal
cd ms-account
./gradlew bootRun
```

Por defecto las aplicaciones usan perfiles y puertos configurados en `ms-*/src/main/resources/application-*.yml`. Consulta `ms-client/README.md` y `ms-account/README.md` para detalles.

## Kafka topics

Topics requeridos por la plataforma:

- `account-created`
- `movement-created`

Estos son creados por `kafka-init` en `docker-compose.yml` o por un Job/InitContainer en Kubernetes (ver F02).

## Database & Migrations

- PostgreSQL (puerto 5432)
- Schema creado mediante Flyway (scripts en `src/main/resources/db/migration` de cada módulo)
- Seed data opcional en migraciones `V*_seed.sql`

## Observability

- Endpoints Actuator: `/actuator/health`, `/actuator/metrics`, `/actuator/prometheus`
- Configura Prometheus para scrapear las apps (anotaciones en manifiestos K8s sugeridas en F02).

## Testing

- Ejecutar tests unitarios y de integración:

```bash
./gradlew test
```

- Recomendación: usar Testcontainers para pruebas E2E que requieren Postgres/Kafka en CI.

## Environment & Secrets

- Documentación y ejemplo de `.env` en F03. No comitear `.env` con secretos.
- En Kubernetes usar `Secret` y `ConfigMap`.

## Useful scripts

- `scripts/check-env.sh` — valida que variables de entorno necesarias estén definidas.
- `scripts/check-topics.sh` — (si existe) valida que los topics requeridos estén presentes en Kafka.

## Acceptance criteria

1. Existe documentación central (este archivo) con pasos reproducibles.
2. `docker-compose up` (con `.env`) arranca infra y servicios y éstas responden en sus endpoints de health.
3. Topics Kafka requeridos son creados automáticamente por `kafka-init` o mediante Job en K8s.
4. Flyway aplica migraciones y seeds correctamente.

## Next steps

- Añadir `scripts/check-env.sh` y `scripts/check-topics.sh` si aún no están en el repo.
- Añadir CI job que ejecute los checks (build/tests + optional newman).
- Crear K8s Job/Init manifest para topic creation en despliegues.

---

Si quieres que cree los scripts de validación (`check-env.sh` y `check-topics.sh`) ahora y los añada al repo, lo hago a continuación.

### 10. Postman Instructions
