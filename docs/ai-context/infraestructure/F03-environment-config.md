# F03 — Configuración de Entorno

## Descripción breve

Definir y documentar las variables de entorno necesarias para ejecutar los microservicios (`ms-client`, `ms-account`) tanto en entorno local (docker-compose) como en Kubernetes. Incluir ejemplos `.env`, mapeos en `docker-compose.yml`, cómo crear `Secrets` en K8s, perfiles (`local`, `prod`) y pasos de validación.

## Objetivos

- Centralizar la configuración en variables de entorno y archivos de propiedades.
- Evitar valores hardcoded en el código o en manifests.
- Proveer instrucciones claras para desarrolladores y para CI/CD.

## Reglas

- No almacenar credenciales en el repositorio.
- Usar variables de entorno y `application-*.yml` por perfil.
- En Spring Boot usar `@ConfigurationProperties` para binding tipado.
- Proveer `.env.example` con placeholders, no con secretos reales.

## Variables por componente (lista recomendada)

Comunes (ambos servicios):

- `SPRING_PROFILES_ACTIVE` — perfil activo (`local`, `prod`, ...)
- `SERVER_PORT` — puerto HTTP del contenedor (opcional)
- `SPRING_DATASOURCE_URL` — JDBC URL (ej: `jdbc:postgresql://postgres:5432/hexabank`)
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `SPRING_JPA_HIBERNATE_DDL_AUTO` — (preferible `none` y usar Flyway)
- `SPRING_FLYWAY_ENABLED` — true/false

Kafka (productor/consumidor):

- `SPRING_KAFKA_BOOTSTRAP_SERVERS` — ej: `kafka:9092`
- `SPRING_KAFKA_CONSUMER_GROUP_ID` — id de grupo por servicio
- `SPRING_KAFKA_AUTO_OFFSET_RESET` — earliest/latest

Otros (opcional):

- `LOG_LEVEL_ROOT` — nivel de logs
- `MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE` — ej: `health,info,prometheus`

## .env.example (plantilla)

```env
# Base DB
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/hexabank
SPRING_DATASOURCE_USERNAME=hexabank
SPRING_DATASOURCE_PASSWORD=changeme

# Kafka
SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092
SPRING_KAFKA_CONSUMER_GROUP_ID=ms-client-group

# Profiles
SPRING_PROFILES_ACTIVE=local

# App
SERVER_PORT=8080
```

Guarda tu `.env` local (no commitear) con las credenciales reales.

## Mapping en `docker-compose.yml`

En `docker-compose.yml` inyectar las variables relevantes en cada servicio. Ejemplo resumido:

```yaml
services:
  ms-client:
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/hexabank
      - SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
      - SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}
      - SPRING_KAFKA_BOOTSTRAP_SERVERS=${SPRING_KAFKA_BOOTSTRAP_SERVERS}
```

Coloca un fichero `.env` en la raíz con las variables (Docker Compose cargará automáticamente en la mayoría de setups) o exporta las variables en tu shell.

## Kubernetes — Secrets y ConfigMaps

- Para valores no sensibles (endpoints, flags) usar `ConfigMap`.
- Para credenciales usar `Secret` (base64). Ejemplo de comando para crear secret:

```bash
kubectl create secret generic db-credentials \
  --from-literal=SPRING_DATASOURCE_USERNAME=hexabank \
  --from-literal=SPRING_DATASOURCE_PASSWORD=hexabank
```

En el `Deployment` referenciar el secret con `envFrom` o `valueFrom`.

## Profiles y archivos de properties

- `application.yml` — valores comunes.
- `application-local.yml` — overrides para desarrollo (H2 o conexiones locales).
- `application-prod.yml` — valores para producción (no contener secretos; referenciar secrets).

## Validaciones y chequeos automáticos

Agregar pequeños checks que CI o el developer pueden ejecutar:

- `scripts/check-env.sh` — verifica que las variables necesarias estén definidas en el entorno.
- `scripts/check-topics.sh` — (recomendado) lista topics y valida que `account-created` y `movement-created` existan.

Ejemplo rápido de `check-env.sh`:

```bash
#!/usr/bin/env bash
set -e
required=(SPRING_DATASOURCE_URL SPRING_DATASOURCE_USERNAME SPRING_DATASOURCE_PASSWORD SPRING_KAFKA_BOOTSTRAP_SERVERS)
missing=()
for v in "${required[@]}"; do
  if [ -z "${!v}" ]; then
    missing+=("$v")
  fi
done
if [ ${#missing[@]} -ne 0 ]; then
  echo "Missing variables: ${missing[*]}"
  exit 1
fi
echo "Environment looks good"
```

## Pasos de validación (local)

1. Crear `.env` a partir de `.env.example` y exportarlo en tu shell o colocarlo en la raíz.
2. Levantar infra mínima:

```bash
docker-compose up -d postgres zookeeper kafka
```

3. Ejecutar topic initializer (si está configurado):

```bash
docker-compose up --no-deps --force-recreate kafka-init
```

4. Verificar variables:

```bash
./scripts/check-env.sh
```

5. Levantar servicios:

```bash
docker-compose up --build ms-client ms-account
```

6. Comprobar health endpoints:

```bash
curl -sS http://localhost:8081/actuator/health | jq
curl -sS http://localhost:8082/actuator/health | jq
```

## Seguridad y mejores prácticas

- No guardar `.env` con secretos en el repo. Añadir `.env` a `.gitignore`.
- Usar Vault/Secrets manager en entornos productivos.
- Evitar exponer variables sensibles en logs.

## Acceptance Criteria (F03)

1. Existe `.env.example` con todas las variables necesarias documentadas.
2. `docker-compose.yml` mapea variables en cada servicio y funciona con un `.env` local.
3. Documentación incluye pasos para crear K8s Secrets y para validar las variables en local.
4. Los scripts `scripts/check-env.sh` y `scripts/check-topics.sh` (opcional) están disponibles y marcan fallo si falta algo.

## Próximos pasos sugeridos

- Añadir `scripts/check-env.sh` y `scripts/check-topics.sh` al repo y documentar su uso.
- Integrar la verificación de env en el pipeline CI (job que falle si faltan variables esenciales).
- Incluir un `README.md` breve en `/docs/` que centralice estos pasos para desarrolladores y SREs.
