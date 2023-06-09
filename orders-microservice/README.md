# Context

This is part of the Awesome Backend saga well described
on [vinisantos.dev](https://vinisantos.dev/posts/awesome-backend)

## Orders Microservice (Java/Spring Boot)

Its responsibility as a Microservice is to resolve anything related to the Orders' context of the
fictional Awesome Online Store.

### Features

It exposes Rest API endpoints allowing

- Registering Orders
- Fetching registered Orders

### Running

Pre-requisites:

1. Have docker or Java 15 environment set up;
2. Have running Apache Kafka on localhost:9092 - the app will start up without it but it's going to
   have errors for executions which ends up producing a Kafka message.

This app is containerized, so if you have docker, you can create an image running something like:  
`docker build -t aos/orders-microservice:latest .`  
Then run a container for it:  
`docker run --network="host" --name orders-microservice -d -p 8210:8210 aos/orders-microservice:latest`

Or... You can package it:  
`mvn clean package`  
Then run the jar:
`java --enable-preview -jar target/orders-microservice-1.0.0.jar`  
The "--enable-preview" is necessary since there are Java 15 preview features in the code.

If everything is ok, you can access the swagger-ui at:  
http://localhost:8110/swagger-ui/

### Architecture

Inspired by [this post](https://herbertograca.
com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together /),
where Herberto Graça put together concepts of the most popular Architecture Styles (Ports &
Adapters, Onion, Clean, etc).

This microservice has interaction with an Apache Kafka instance, for integrating with other
microservices in an event-driven pattern.

The messages/events flow is described well [here](https://vinisantos.dev/posts/awesome-backend-part3-dynamic-kafka-events)
