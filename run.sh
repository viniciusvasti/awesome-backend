#!/bin/bash
# build the projects
cd orders-microservice
mvn clean install
docker build -t aos/orders-microservice:latest .
cd ..
cd stock-microservice
mvn clean install
docker build -t aos/stock-microservice:latest .
cd ..
cd users-microservice
mvn clean install
docker build -t aos/users-microservice:latest .

# docker compose
docker-compose up --abort-on-container-exit