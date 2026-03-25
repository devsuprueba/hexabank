# Feature: Dockerization

## User Story
As a developer,
I want to containerize the microservice,
so that it can be deployed and executed in any environment.

---

## Tasks

### 1. Create Dockerfile

- Use a lightweight Java 21 base image
- Copy the generated JAR file
- Define the entrypoint to run the application

---

### 2. Build Configuration

- Ensure the project generates a JAR file using Gradle
- The JAR must be located in:

  build/libs/

---

### 3. Environment Configuration

- Application must read configuration from environment variables
- Do not hardcode values

---

### 4. Port Exposure

- Expose application port (default 8080)

---

### 5. Logging

- Ensure logs are printed to standard output
- Logs must be readable and structured

---

## Rules

- Dockerfile must be simple and readable
- Avoid unnecessary layers
- Do not include development tools
- Use only required dependencies

---

## Expected Dockerfile Example

The generated Dockerfile must follow this structure:

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

---

## Acceptance Criteria

- Docker image builds successfully
- Container starts without errors
- Application is accessible on defined port
- Logs are visible in container output