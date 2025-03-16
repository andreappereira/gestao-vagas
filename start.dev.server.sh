#!/bin/sh

echo 'Waiting for postgres...'

while ! nc -z $POSTGRES_HOST $POSTGRES_PORT; do
    sleep 0.1
done

echo 'PostgreSQL started'

echo 'Starting Java application...'

# mvn spring-boot:run
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

echo 'Java application started!'
