# IT Support Ticket System

A ticket management system with a Spring Boot backend and Swing client interface.

## Prerequisites

- Docker and Docker Compose
- Java 17 or higher
- Maven

## Building and Running

### Backend

1. Build the application:

```bash
mvn clean package
```

2. Start the containers:

```bash
docker-compose up -d
```

The backend will be available at `http://localhost:8080`

### Swing Client

Run the client JAR:

```bash
java -jar target/it-support-system-1.0-SNAPSHOT-client.jar
```

## Development

- Backend code is in `src/main/java/com/itsupport`
- Swing client code is in `src/main/java/com/itsupport/ui`
- Database schema in `src/main/resources/schema.sql`

## API Documentation

Swagger UI available at: `http://localhost:8080/swagger-ui.html`
