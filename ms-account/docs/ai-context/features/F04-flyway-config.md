# F04 - Flyway Configuration

Purpose

- Standardize how database migrations are created, reviewed and applied for `ms-account`.

Location

- All versioned migrations live under `src/main/resources/db/migration/` (this module follows Flyway conventions).

Naming and ordering

- Use Flyway versioned filenames: V{version}__{description}.sql
  - Examples:
    - `V1__init_schema.sql`
    - `V2__add_indexes.sql`
    - `V3__add_constraints.sql`
- Version numbers must increase monotonically and be integers. Prefer simple increasing integers (1,2,3...) or date-based (20260326_01) for teams that prefer that style.

Rules for writing migrations

- Never edit an already-applied migration script. If you need to change schema/behavior, create a new migration.
- Keep migrations small and focused: one logical change per migration.
- Add a comment header with intent and any backward-compatibility notes.
- Avoid irreversible destructive changes when possible; document required manual steps for data migrations.

SQL style guidelines

- Use explicit column types and `NOT NULL` where applicable.
- Add indexes with names: `idx_{table}_{column}`.
- Add constraints with explicit names: `chk_{table}_{column}_not_empty`, `fk_{child}_{parent}`.

Testing migrations

- Run migrations against a clean in-memory H2 during tests using the `test` profile.
- Add an integration test that boots Spring Boot with the `test` profile and asserts Flyway reports the latest version applied.

Flyway configuration (application.properties example)

```properties
# Flyway locations are defaulted but declared explicitly for clarity
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
# Optional: set schemas and placeholders if needed
# spring.flyway.schemas=public
# spring.flyway.placeholders.some_value=...
```

Review process

- Include the SQL migration in the feature branch and explain the rationale in the PR description.
- Ask for DB-focused review on migrations that modify existing data or remove columns.

Rollback strategy

- Flyway does not provide automatic rollbacks for versioned migrations. To rollback, add a new migration that reverts the previous change or restore from backups as appropriate.

CI/Release notes

- CI should run `./gradlew clean build` and fail if migrations can't be applied to a fresh DB.
- Document in release notes which migration versions are included when schema changes are delivered.

Acceptance criteria

- Migrations are versioned and placed in `src/main/resources/db/migration`.
- `./gradlew clean bootJar` and running the app with a fresh DB applies all migrations and leaves the schema in the expected state.
- Tests validate that Flyway applied the expected version during automated runs.

Next steps

- I can add a small integration test `FlywayMigrationIT` that starts the app with an in-memory DB and asserts Flyway's `info` indicates the latest version applied. I can also add a sample `V2__example_change.sql` showing a non-destructive change if you want.
