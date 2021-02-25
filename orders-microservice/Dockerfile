FROM adoptopenjdk/openjdk15:ubi

MAINTAINER Vinicius A Santos

VOLUME /config

ADD target/orders-microservice-1.0.0.jar orders-microservice-1.0.0.jar

# COPY target/classes/logback.xml /config/

COPY target/classes/config/* /config/
COPY target/classes/data-h2.sql /

# ENV JAVA_OPTS="-Xmx256m -Xms256m -XX:MetaspaceSize=48m -XX:+UseG1GC -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Dlogging.config=file:/config/logback.xml -Djava.security.egd=file:/dev/./urandom"

# ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar rchlo-microservices.jar" ]
ENTRYPOINT ["java","--enable-preview","-jar","/orders-microservice-1.0.0.jar"]