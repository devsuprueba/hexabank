-- Local-only Flyway SQL migration to advance sequences after seed data.
-- This file is intentionally local (do NOT push automatically). It is idempotent
-- and safe to run on PostgreSQL. It will advance the serial sequences for the
-- `account` and `movement` tables so nextval() doesn't collide with seeded ids.

SELECT setval(pg_get_serial_sequence('account','id'), COALESCE((SELECT MAX(id) FROM account), 0), true);
SELECT setval(pg_get_serial_sequence('movement','id'), COALESCE((SELECT MAX(id) FROM movement), 0), true);
