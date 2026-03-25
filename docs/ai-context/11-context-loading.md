# Context Loading Rules

## Objective

Ensure the agent always uses the correct context before implementing any feature.

---

## Mandatory Context

Before implementing any feature, the agent MUST load and follow:

- 00-global-rules.md
- 01-architecture.md
- 02-developer-profile.md
- 03-testing-profile.md
- 04-agent-rules.md
- 05-deployment.md
- 06-sonar-rules.md
- 07-checkstyle-config.md
- 08-execution-plan.md
- 09-definition-of-done.md
- 10-git-workflow.md

---

## Feature Context

After loading global context, the agent must:

- Load the specific feature definition (FXX)
- Implement it respecting all global rules

---

## Rules

- Do not ignore global rules
- Do not override architecture
- Do not assume missing requirements
- Ask when something is unclear

---

## Execution Order

1. Load global context
2. Load feature definition
3. Implement feature
4. Validate feature
5. Commit and push

---

## Final Rule

A feature is not valid if it does not comply with global context.