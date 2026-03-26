# ms-account

This module implements the `ms-account` microservice (work in progress).

Quick start

- Build the module:

```bash
./gradlew build
```

- Run tests:

```bash
./gradlew test
```

- Run the application locally (after build):

```bash
./gradlew bootJar
java -jar build/libs/account-0.0.1-SNAPSHOT.jar
```

Notes

- Use the module Gradle wrapper (`./gradlew`) to ensure the correct Gradle version.
- For local dev, use the `test` profile and H2 in-memory DB:
  -Dspring.profiles.active=test -Dspring.datasource.url=jdbc:h2:mem:testdb

Docs

- See `docs/ai-context/features` for feature specifications F00..F21.
