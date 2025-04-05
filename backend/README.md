# TODO Backend

Spring Boot todo app backend providing simple REST API.

## Get started

To run the backend server:

```bash
$ ./gradlew bootRun
```

Once the server is running, you can explore the API documentation
at http://localhost:8080/swagger-ui/index.html or connect to it from the
frontend.

To run tests:

```bash
$ ./gradlew test
```

## Architecture overview

This project follows a classic three-tier architecture and provides a CRUD REST
API that adheres to the OpenAPI specification, defined in
`src/main/resources/static/openapi.yaml`.

To streamline development, the backend includes an abstract `CrudService` and
`CrudController`, which implement basic CRUD operations for any `DomainEntity`.
While the architecture may feel overengineered for its current simplicity, it
lays the groundwork for scaling and extending the application in the future.

Entities are persisted in in-memory database by default. There is a config
specifically for use by Docker Compose which uses PostgresSQL database inside
another Docker container.

### Current limitations

- Basic error handling
- Basically no business logic beyond simple CRUD

### Technologies

- Spring Boot
- JPA/Hibernate
- OpenAPI/Swagger
- H2 Database for development
- PostgresSQL for "production"
- Gradle