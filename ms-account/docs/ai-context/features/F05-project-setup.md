# F05 - Project setup

## Objetivo

Definir la configuración mínima y los comandos recomendados para desarrollar, compilar, ejecutar y probar el servicio `ms-account` localmente y en CI.

## Contrato mínimo

- Inputs: código fuente Java 21, archivos de recursos y migraciones en `src/main/resources`.
- Outputs: artefacto ejecutable (`bootJar`), pruebas (unit e integración) que pasan, y documentación clara para ejecutar el servicio localmente.
- Modos de error: fallos de compilación, fallos de Checkstyle, fallos en tests o migraciones.

## Comandos básicos (desde la raíz del módulo `ms-account`)

- Compilar y ejecutar tests:

```
./gradlew build
```

- Ejecutar solo tests:

```
./gradlew test
```

- Ejecutar la aplicación localmente (profile `local` o `dev` según convención):

```
./gradlew bootRun --args='--spring.profiles.active=local'
```

- Empaquetado (jar):

```
./gradlew bootJar
```

## Entorno de desarrollo

- Recomendado: IntelliJ IDEA (o VS Code) con soporte para Gradle y Lombok.
- Importar el proyecto como un proyecto Gradle y habilitar la compatibilidad con annotation processors (Lombok).
- Añadir la carpeta `config/checkstyle` como configuración de Checkstyle en el IDE para replicar las reglas de CI.

## Perfiles y variables de entorno

- `test` (ya existe): usa H2 en memoria y aplica migraciones Flyway; se usa para tests de integración.
- `local` / `dev`: conectar a PostgreSQL local (o contenedor) con las credenciales apropiadas.

Ejemplo de variables env para `local`:

- SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/accountdb
- SPRING_DATASOURCE_USERNAME=account
- SPRING_DATASOURCE_PASSWORD=secret
- SPRING_PROFILES_ACTIVE=local

## Docker (opcional)

Se recomienda contenerizar la app en CI/CD. Un Dockerfile típico (simplificado):

1. Build stage: usar la `bootJar` para producir el jar.
2. Runtime stage: imagen base ligera (OpenJDK 21 JRE o equivalente) y copiar el jar.

En CI preferir multi-stage build y almacenar el jar como artefacto.

## CI / pipeline hints

- Ejecutar `./gradlew build` y publicar resultados de test y checkstyle.
- Validar Checkstyle y failing fast para reglas críticas (naming, imports, line length).
- Ejecutar las pruebas de integración que usan el profile `test` (Flyway + H2) para garantizar que las migraciones aplican correctamente.

## Migraciones y despliegue

- Las migraciones Flyway se colocan en `src/main/resources/db/migration/` con el prefijo `V` seguido de la versión (por ejemplo `V1__init_schema.sql`).
- En despliegues, Flyway debe ejecutarse en arranque (valor por defecto en Spring Boot) y reportar errores en caso de migraciones fallidas para evitar iniciar con esquema inconsistente.

## Notas de debugging rápidas

- Si Flyway no aplica migraciones en local: comprobar `spring.flyway.locations` y la URL de la base de datos.
- Si Checkstyle falla en CI pero pasa local: asegúrate de usar la misma JDK/formatter y que el IDE no está auto-corrigiendo imports (no usar wildcard imports).
- Para problemas con Lombok: confirmar que `lombok.config` está presente y que annotation processing está habilitado en el IDE.

## Próximos pasos recomendados

1. Añadir un `Dockerfile` de ejemplo y/o `docker-compose.yml` para levantar PostgreSQL + ms-account en desarrollo.
2. Añadir un pipeline de CI (GitHub Actions / GitLab CI) que ejecute `./gradlew build` y publique artefactos.
3. Crear una guía de debugging para errores comunes (migraciones, checkstyle, JPA mapping issues).

## Aceptación

- Documento agregado al repositorio bajo `docs/ai-context/features/F05-project-setup.md`.
- `./gradlew build` debe pasar en la rama tras cualquier cambio no trivial.

# Feature: Project Setup

## User Story

As a developer,
I want to initialize the microservice structure,
so that development can start on a stable base.

---

## Tasks

- Create Spring Boot project
- Define package structure
- Configure base application class
- Create initial application.yml
- Ensure project starts correctly

---

## Rules

- Use Java 21
- Use Gradle
- Keep the project minimal at bootstrap stage

---

## Acceptance Criteria

- Project starts successfully
- Base structure is in place
