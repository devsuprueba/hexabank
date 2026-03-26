-- V1 initial schema for ms-account
CREATE TABLE account (
    id BIGSERIAL PRIMARY KEY,
    owner_name VARCHAR(200) NOT NULL,
    balance NUMERIC(19,2) NOT NULL DEFAULT 0
);

-- Example index
CREATE INDEX idx_account_owner_name ON account(owner_name);
