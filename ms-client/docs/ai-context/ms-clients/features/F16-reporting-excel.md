# Feature: Reporting with Excel

## User Story
As a user,
I want to generate reports,
so that I can analyze client data.

---

## Tasks

### 1. JSON Report Endpoint

- GET /reports
- Return filtered client data

---

### 2. Excel Report Endpoint

- GET /reports/excel

---

### 3. Excel Generation

- Generate Excel file with:

    - Headers
    - Structured rows
    - Consistent format

---

## Excel Rules

- Columns must be clearly named
- Data must be aligned with JSON response

---

## Performance

- Avoid loading unnecessary data
- Use streaming if dataset is large

---

## Logging

- Log report generation
- Include filters and result size

---

## Acceptance Criteria

- JSON endpoint works
- Excel file downloads correctly
- Data is consistent in both formats