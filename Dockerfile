FROM eclipse-temurin:21-jdk-alpine
LABEL authors="Mi"

WORKDIR /eventHub

COPY build/libs/EventHub-0.0.1-SNAPSHOT.jar EventHub-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "EventHub-0.0.1-SNAPSHOT.jar"]