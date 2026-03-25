# Feature: Dependencies Setup

## User Story
As a developer,
I want to configure all required dependencies,
so that the project is ready for development and follows quality standards.

---

## Tasks

### 1. Add Core Dependencies

Include:

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- PostgreSQL Driver
- Spring Boot Starter Actuator

---

### 2. Add Event-Driven Dependencies

- Spring for Apache Kafka

---

### 3. Add Database Migration

- Flyway Core

---

### 4. Add Documentation

- Springdoc OpenAPI (Swagger)

---

### 5. Add Resilience

- Resilience4j

---

### 6. Add Lombok

- Lombok dependency
- Lombok annotationProcessor

---

### 7. Add Testing Dependencies

- Spring Boot Starter Test

---

## Gradle Configuration

### Plugins

- java
- org.springframework.boot
- io.spring.dependency-management
- checkstyle

---

### Java Version

- Java 21 must be configured

---

### Lombok Configuration

- Enable annotationProcessor
- Ensure compatibility with IDE

---

## Build Rules

- Project must build successfully using:

  ./gradlew build

- Checkstyle must be configured and executed during build

---

## Dependency Rules

- Do not include unnecessary dependencies
- Keep dependencies minimal and relevant
- Avoid overlapping libraries

---

## Configuration Rules

- All external configurations must be defined in application.yml
- No hardcoded configuration values

---

## Acceptance Criteria

- Project builds successfully
- All dependencies are resolved
- Lombok works correctly
- Kafka, JPA and Flyway are available
- Checkstyle runs during build