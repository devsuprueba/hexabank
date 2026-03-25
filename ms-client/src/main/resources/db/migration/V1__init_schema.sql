-- V1__init_schema.sql - initial schema
-- Must be idempotent

CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS persona (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(255)
);
