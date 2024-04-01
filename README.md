# Gestao Vagas
Gestao Vagas is a Java Spring Boot application designed to for job vacancies management.

## Technologies Used
- Java: The core programming language used for backend development.

- Spring Boot: Framework for building and running Java-based applications.

- PostgreSQL: Relational database management system used to store financial data.

- Spring Data JPA: Part of the Spring Data project, provides easy and standardized data access using JPA (Java Persistence API).


# Getting Started
Clone the Repository:
```
bash
Copy code
git clone https://github.com/andreappereira/gestao-vagas.git
```
## Run the PostgreSQL Database with Docker Compose:

Navigate to the root directory of the project.
Run the following command to start the PostgreSQL database using Docker Compose:
```
docker-compose up -d
```
## Set Up the Application:

Open the application.properties file located in src/main/resources.
Configure the database connection properties to match the PostgreSQL database running in Docker Compose.

## Build and Run the Application:
```
mvn spring-boot:run
```

## Access the Application:
Open a web browser and navigate to http://localhost:8080 to access the application.

### License
This project is licensed under the MIT License - see the LICENSE file for details.
