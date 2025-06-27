# Базовый образ с Java21 и Apline Linux
FROM eclipse-temurin:21-jdk-alpine
LABEL authors="Mi"

# Установка рабочей директории внутрь eventHub. Теперь мы внутри контейнера.
WORKDIR /eventHub

COPY build/libs/EventHub-1.jar EventHub-1.jar

ENTRYPOINT ["java", "-jar", "EventHub-1.jar"]