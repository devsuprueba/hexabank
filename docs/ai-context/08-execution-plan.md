# Execution Plan for AI Agent

## Objective

The agent must implement the system step by step following the defined features.

---

## Rules

- DO NOT skip steps
- DO NOT implement multiple features at once
- COMPLETE one feature before moving to the next
- VALIDATE each feature before continuing

---

## Execution Order

1. F00-dependencies-setup
2. F01-build-config
3. F02-lombok-config
4. F03-jpa-config
5. F04-flyway-config

6. F05-project-setup
7. F06-architecture-base

8. F07-flyway-initial-schema

9. F08-domain-model
10. F09-repository-layer
11. F10-application-usecases
12. F11-rest-api

13. F12-exception-handling
14. F13-logging-mdc

15. F14-kafka-provider
16. F15-kafka-listener

17. F16-reporting-excel

18. F17-testing

19. F18-flyway-evolution

20. F19-dockerization
21. F20-kubernetes
22. F21-observability

---

## Validation Rules

After each feature:

- The project must compile
- No errors must exist
- Code must follow rules
- No TODO or incomplete code

---

## Stop Condition

If any requirement is unclear:
ASK before continuing