-- V5: seed data for accounts and movements (F23)

-- Insert sample accounts (ids chosen to be stable for FK references)
INSERT INTO account (id, client_id, account_number, account_type, owner_name, initial_balance, current_balance, status, version, created_at, updated_at) VALUES
(1, 1, '478758', 'Ahorros', 'Jose Lema', 2000.00, 2000.00, 'ACTIVE', 0, '2022-02-01 00:00:00', '2022-02-01 00:00:00'),
(2, 2, '225487', 'Corriente', 'Marianela Montalvo', 100.00, 100.00, 'ACTIVE', 0, '2022-02-01 00:00:00', '2022-02-01 00:00:00'),
(3, 3, '495878', 'Ahorros', 'Juan Osorio', 0.00, 0.00, 'ACTIVE', 0, '2022-02-01 00:00:00', '2022-02-01 00:00:00'),
(4, 2, '496825', 'Ahorros', 'Marianela Montalvo', 540.00, 540.00, 'ACTIVE', 0, '2022-02-01 00:00:00', '2022-02-01 00:00:00');

-- Insert sample movements
INSERT INTO movement (id, account_id, movement_date, movement_type, amount, balance_after_movement, created_at) VALUES
(1, 1, '2022-02-05 10:00:00', 'WITHDRAWAL', -575.00, 1425.00, '2022-02-05 10:00:00'),
(2, 2, '2022-02-10 12:00:00', 'DEPOSIT', 600.00, 700.00, '2022-02-10 12:00:00'),
(3, 3, '2022-02-06 09:00:00', 'DEPOSIT', 150.00, 150.00, '2022-02-06 09:00:00'),
(4, 4, '2022-02-08 15:30:00', 'WITHDRAWAL', -540.00, 0.00, '2022-02-08 15:30:00');

-- After inserting explicit ids for BIGSERIAL columns, ensure sequences are advanced
-- so future inserts using nextval() do not collide with seeded ids.
SELECT setval(pg_get_serial_sequence('account','id'), COALESCE((SELECT MAX(id) FROM account), 0), true);
SELECT setval(pg_get_serial_sequence('movement','id'), COALESCE((SELECT MAX(id) FROM movement), 0), true);
