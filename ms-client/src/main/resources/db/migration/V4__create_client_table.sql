-- Flyway migration: create client table
CREATE TABLE IF NOT EXISTS client (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  identification VARCHAR(100) NOT NULL UNIQUE,
  gender VARCHAR(20),
  age INTEGER,
  address VARCHAR(300),
  phone VARCHAR(50),
  status BOOLEAN DEFAULT true
);
