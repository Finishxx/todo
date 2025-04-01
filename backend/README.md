# TODO Backend

his project follows a classic three-tier architecture and provides a CRUD REST
API that adheres to the OpenAPI specification, defined in
`src/main/resources/static/openapi.yaml`.

To streamline development, the backend includes an abstract `CrudService` and
`CrudController`, which implement basic CRUD operations for any DomainEntity.
These components, along with corresponding DTOs and entity-DTO conversion
functions, simplify the addition of new entities.

Entities are persisted in in-memory database by default. There is a config
specifically for use by Docker Compose which uses PostgresSQL database inside
another Docker container.

## Get started

### Run the server

To start the backend, run:

```bash
$ ./gradlew bootRun
```

Once the server is running, you can explore the API documentation
at http://localhost:8080/swagger-ui/index.html or connect to it from the
frontend.

### Run tests

To execute tests, use:

```bash
$ ./gradlew test
```