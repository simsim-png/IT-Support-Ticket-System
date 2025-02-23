#!/bin/bash

# Build the application
mvn clean package -DskipTests

# Build and start the containers
docker build -t it-support-system:1.0 .
docker-compose up 