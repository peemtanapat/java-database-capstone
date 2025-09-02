# Build
FROM maven:3.9.11-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY app/pom.xml pom.xml
COPY app/src src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

COPY --from=build /app/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT  [ "java", "-jar", "app.jar" ]
