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
git clone https://github.com/simsim-png/IT-Support-Ticket-System.git
cd IT-Support-Ticket-System
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

Demo : 
![1_1](https://github.com/user-attachments/assets/bde38ed2-156b-4b38-b1d3-d00a28fc07ca)
![1_2](https://github.com/user-attachments/assets/c0cc9d5e-0c40-45a6-a99e-7367f0efbc19)
![1_3](https://github.com/user-attachments/assets/842dc30c-a2bd-46b5-9d23-f540a1988625)
![1_4](https://github.com/user-attachments/assets/82c0a646-0331-4e55-b7ff-82e7b7823a10)
![1_5](https://github.com/user-attachments/assets/ec5a4d3c-434d-40a9-9b75-86572104d09b)


