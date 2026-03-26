-- V4: enforce client_id NOT NULL on account table

-- Set any existing null client_id to a sentinel (0) or fail depending on policy; here we set to 0 to preserve existing rows
UPDATE account SET client_id = 0 WHERE client_id IS NULL;

-- Then alter column to NOT NULL
ALTER TABLE account ALTER COLUMN client_id SET NOT NULL;
