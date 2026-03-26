-- V2 add movement table and extend account schema (H2-compatible)

-- Add new columns to account (one statement per column to be H2-friendly)
ALTER TABLE account ADD COLUMN client_id BIGINT;
ALTER TABLE account ADD COLUMN account_number VARCHAR(50);
ALTER TABLE account ADD COLUMN account_type VARCHAR(50);
ALTER TABLE account ADD COLUMN initial_balance NUMERIC(19,2) DEFAULT 0;
ALTER TABLE account ADD COLUMN current_balance NUMERIC(19,2) DEFAULT 0;
ALTER TABLE account ADD COLUMN status VARCHAR(50) DEFAULT 'ACTIVE';
ALTER TABLE account ADD COLUMN version INTEGER DEFAULT 0;
ALTER TABLE account ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE account ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Add unique constraint on account_number
ALTER TABLE account ADD CONSTRAINT uq_account_account_number UNIQUE (account_number);

-- Create movement table
CREATE TABLE movement (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    movement_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    movement_type VARCHAR(50) NOT NULL,
    amount NUMERIC(19,2) NOT NULL,
    balance_after_movement NUMERIC(19,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_movement_account FOREIGN KEY (account_id) REFERENCES account(id)
);

-- Index for queries
CREATE INDEX idx_movement_account_id ON movement(account_id);
