FROM maven:3.9.9-eclipse-temurin-17-alpine

WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline

COPY . .

RUN chmod +x /app/start.dev.server.sh
