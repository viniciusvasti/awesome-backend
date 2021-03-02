FROM adoptopenjdk/openjdk15:ubi

MAINTAINER Vinicius A Santos

VOLUME /config

ADD target/orders-microservice-1.0.0.jar orders-microservice-1.0.0.jar

# COPY target/classes/logback.xml /config/

COPY target/classes/config/* /config/
COPY target/classes/data-h2.sql /

ENTRYPOINT ["java","--enable-preview","-jar","/orders-microservice-1.0.0.jar"]
