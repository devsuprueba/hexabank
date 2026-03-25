-- V2__indexes.sql - add indexes for search performance

CREATE INDEX IF NOT EXISTS idx_cliente_nombre ON cliente(nombre);

CREATE INDEX IF NOT EXISTS idx_persona_nombre ON persona(nombre);
