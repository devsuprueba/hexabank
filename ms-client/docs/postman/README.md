# Postman for ms-client

This folder contains a Postman collection and a local environment to exercise the `ms-client` CRUD API.

Files

- `Hexabank-ms-client.postman_collection.json` — CRUD requests (create/list/get/update/delete).
- `Hexabank-local-ms-client.postman_environment.json` — environment with `baseUrl` defaulting to `http://localhost:8081`.

Quickstart

1. Start the application (see module README). Ensure the DB is reachable so Flyway can run migrations — the project includes a seed migration that inserts sample clients (`CL-0001`..`CL-0004`).
2. Import the collection and the environment into Postman.
3. Select the `Hexabank - Local ms-client` environment and run requests in this order:
   - Create client (optional)
   - List clients (seeded clients appear here)
   - Get client by id (use id from created/listed clients)
   - Update client
   - Delete client

Notes

- The seed clients are added by Flyway migration `V5__seed_clients.sql` and will be present after the application starts and Flyway runs.
- If you need a request to lookup clients by `identification`, consider adding GET /api/clients/by-identification/{identification} in the future; currently you can list and filter client by identification locally.
