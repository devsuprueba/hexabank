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

- Enable checkstyle plugin
- Attach checkstyle to build lifecycle

---

### 3. Configure Build Execution

- Build must run:

  ./gradlew build

---

## Rules

- Build must fail if Checkstyle fails
- No warnings allowed in build

---

## Expected Gradle Configuration

plugins {
id 'java'
id 'org.springframework.boot' version '3.2.0'
id 'io.spring.dependency-management' version '1.1.4'
id 'checkstyle'
}

checkstyle {
toolVersion = '10.12.1'
}

---

## Acceptance Criteria

- Build runs successfully
- Checkstyle is executed
- Build fails on violations
