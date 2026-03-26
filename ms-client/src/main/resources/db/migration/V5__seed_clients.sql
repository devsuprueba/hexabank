-- Flyway seed data for ms-client: sample clients for local testing and CI
INSERT INTO client (name, identification, gender, age, address, phone, status) VALUES
('María López','CL-0001','F',29,'Av. Central 123','+56911223344',true),
('Juan Perez','CL-0002','M',35,'Calle Secundaria 45','+56922334455',true),
('Empresa ACME','CL-0003',NULL,NULL,'Av. Industrial 10','',true),
('Test User','CL-0004','M',40,'Test address 1','+56999887766',false);
