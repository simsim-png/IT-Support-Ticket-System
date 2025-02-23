FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR file
COPY target/*.jar app.jar

# Copy the wait-for-it script to handle database startup
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

EXPOSE 8080

# Wait for database to be ready, then start the application
CMD ["./wait-for-it.sh", "db:1521", "--", "java", "-jar", "app.jar"] 