FROM maven:3.9.9-eclipse-temurin-17-alpine

WORKDIR /app

COPY . .

RUN mvn dependency:go-offline

RUN chmod +x /app/start.dev.server.sh
