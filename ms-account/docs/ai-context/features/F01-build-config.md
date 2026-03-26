# Feature: Build Configuration

## User Story

As a developer,
I want a standardized build process,
so that quality and consistency are enforced.

---

## Tasks

### 1. Configure Gradle Plugins

Add:

- java
- org.springframework.boot
- io.spring.dependency-management
- checkstyle

---

### 2. Configure Checkstyle

````markdown
# F01 - Build Configuration

Purpose

- Document Gradle build configuration, required plugins, and how to build the project.

This document describes how to build, test and run the `ms-account` module locally and in CI.

Build system

- Gradle wrapper is available at the module root (`./gradlew`). Use the wrapper to ensure the correct Gradle version.
- Java toolchain is configured for Java 21 in `build.gradle`.

Common tasks

- Build the module (compile, tests, assemble):

```bash
./gradlew build
```
````

- Run tests only:

```bash
./gradlew test
```

- Run check (includes checkstyle):

```bash
./gradlew check
```

- Create executable jar (bootJar):

```bash
./gradlew bootJar
```

Notes about checkstyle

- This module contains a local Checkstyle configuration at `config/checkstyle/checkstyle.xml`. The build is configured to run Checkstyle during `check`.

Running the application locally

- Build first, then run the bootJar:

```bash
./gradlew bootJar
java -jar build/libs/account-0.0.1-SNAPSHOT.jar
```

- For local development prefer the in-memory H2 database. Example system properties to override datasource:

```bash
java -Dspring.profiles.active=test -Dspring.datasource.url=jdbc:h2:mem:testdb -jar build/libs/account-0.0.1-SNAPSHOT.jar
```

CI recommendations

- Run `./gradlew check` on PRs (fail fast on compilation, tests or checkstyle violations).
- Cache Gradle and the `.gradle` and `~/.gradle/caches` between jobs for speed.

Troubleshooting

- If a dependency is missing or a test fails, re-run with `--stacktrace --info` to get more details.

TODO: add CI workflow YAML (GitHub Actions) if you want CI scaffolding committed in this repo.

```

```
