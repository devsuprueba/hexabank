-- V3: add useful constraints and indexes for account and movement

-- Ensure status contains only allowed values
ALTER TABLE account
    ADD CONSTRAINT chk_account_status_allowed CHECK (status IN ('ACTIVE', 'INACTIVE'));

-- Ensure movement_type is constrained to known values
ALTER TABLE movement
    ADD CONSTRAINT chk_movement_type_allowed CHECK (movement_type IN ('DEPOSIT','WITHDRAWAL'));

-- Add index on movement_date to speed up range queries used by reports
CREATE INDEX IF NOT EXISTS idx_movement_movement_date ON movement(movement_date);

-- Add composite index for account_id + movement_date for typical account statement queries
CREATE INDEX IF NOT EXISTS idx_movement_account_date ON movement(account_id, movement_date DESC);

-- Ensure account_number length and not empty (use check to avoid failing existing nulls)
ALTER TABLE account
    ADD CONSTRAINT chk_account_number_not_empty CHECK (account_number IS NULL OR account_number <> '');
