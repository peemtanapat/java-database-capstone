# Build
FROM maven:3.9.11-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY app/pom.xml pom.xml
COPY app/src src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

ENV SPRING_PROFILES_ACTIVE=docker
ENV PORT=8080

COPY --from=build /app/target/*.jar app.jar

EXPOSE $PORT

ENTRYPOINT  [ "java", "-jar", "app.jar" ]
