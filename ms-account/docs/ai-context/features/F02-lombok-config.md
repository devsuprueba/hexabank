# F02 - Lombok Configuration

Purpose

- Describe how Lombok is configured for `ms-account`, how annotation processing is enabled, and IDE tips.

What we configure

- Add Lombok as `compileOnly` and `annotationProcessor` in `build.gradle`. This repository already includes:

```groovy
compileOnly 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'
```

- Add a `lombok.config` file at the module root to enforce consistent Lombok behavior across IDEs and CI.

Recommended `lombok.config`

```properties
config.stopBubbling=true
lombok.anyConstructor.addConstructorProperties=true
lombok.accessors.chain=true
lombok.setter.flagUsage=ERROR
lombok.noArgsConstructor.extraPrivate=false
```

IDE setup

- IntelliJ IDEA: Install Lombok plugin and enable "Annotation processors" in Settings → Build, Execution, Deployment → Compiler → Annotation Processors.
- Eclipse/STS: Install Lombok jar (run lombok.jar) or enable annotation processing in project settings.

Troubleshooting

- If generated methods (getters/setters/builders) are not visible in IDE, enable annotation processing and restart the IDE.
- If Checkstyle/formatters flag Lombok-generated code, ensure Checkstyle configuration ignores generated sources, or configure Lombok to reduce compatibility issues.

Acceptance criteria

- `./gradlew build` completes successfully with Lombok enabled.
- IDEs recognize Lombok-generated code after enabling annotation processing and installing the Lombok plugin.

Next steps

- If you prefer stricter Lombok rules (no setters, explicit @Builder usage), I can add examples and a pre-commit check to enforce them.
