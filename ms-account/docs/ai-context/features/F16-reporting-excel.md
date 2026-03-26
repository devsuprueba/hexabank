# Feature: Reporting with Excel

## User Story

As a user,
I want to generate account statements in JSON and Excel,
so that I can review account activity in different formats.

---

## Tasks

### 1. JSON Report Endpoint

Implement:

- GET /reports

Return account statement filtered by:

- clientId
- startDate
- endDate

### 2. Excel Report Endpoint

Implement:

- GET /reports/excel

### 3. Report Content

The report must include:

- client identifier
- associated accounts
- current balances
- movement details
- movement dates
- movement amounts
- balance after movement

### 4. Excel Generation

- Generate a structured Excel file
- Use clear headers
- Keep content aligned with JSON report

---

## Rules

- Report belongs to ms-account
- Use native queries if needed for efficient reads
- Log report generation
- Keep Excel generation in infrastructure/report layer

---

## Acceptance Criteria

- JSON report works correctly
- Excel download works correctly
- Data is consistent between JSON and Excel
