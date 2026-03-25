# Feature: Git Workflow

## Objective

Ensure consistent, professional and traceable commits across the entire system.

---

## Scope

This workflow applies to:

- ms-clients
- ms-accounts
- infrastructure

---

## Commit Strategy

- One commit per feature
- Do not group multiple features
- Commit only after feature is fully implemented and validated

---

## Validation Before Commit

Before committing, ensure:

- Code compiles successfully
- No build errors
- Checkstyle passes
- No TODOs or incomplete code
- Feature acceptance criteria is met

---

## Commit Format (MANDATORY)

type(scope): short description

---

## Allowed Types

- feat: new functionality
- fix: bug fix
- refactor: internal improvement
- chore: configuration or setup
- test: testing related changes

---

## Scope Definition

Scope must represent the feature or layer:

- domain
- repository
- usecase
- api
- kafka
- docker
- k8s
- config

---

## Commit Message Rules

- Must be in English
- Must be concise
- Must describe intent clearly
- No emojis or informal language

---

## Examples

feat(domain): implement cliente domain model

feat(usecase): add create client use case

feat(api): expose client endpoints

feat(kafka): publish client created event

chore(docker): add dockerfile configuration

test(api): add integration tests for client endpoints

---

## Execution

Use helper script:

./scripts/git-commit.sh <type> <scope> "<message>"

Example:

./scripts/git-commit.sh feat api "add client endpoints"

---

## Push Strategy

After each commit:

git push origin main

---

## Restrictions

- Do not commit unfinished code
- Do not commit commented or temporary code
- Do not commit generated artifacts (build, logs, etc.)

---

## Branching Strategy

- Use main branch for simplicity (test requirement)
- Keep commits small and incremental

---

## Final Rule

A feature is considered complete only when:

- Code compiles
- Tests pass
- Code follows all global rules
- Feature acceptance criteria is satisfied

Only then:
commit and push