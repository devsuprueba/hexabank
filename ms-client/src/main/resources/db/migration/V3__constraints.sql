-- V3__constraints.sql - add constraints to ensure nombre is present

ALTER TABLE cliente
    ADD CONSTRAINT chk_cliente_nombre_not_empty CHECK (nombre IS NOT NULL AND nombre <> '');

ALTER TABLE persona
    ADD CONSTRAINT chk_persona_nombre_not_empty CHECK (nombre IS NOT NULL AND nombre <> '');
