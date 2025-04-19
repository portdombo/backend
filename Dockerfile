# Build stage
FROM maven:3-amazoncorretto-21-alpine AS build
WORKDIR /app

COPY adapters/pom.xml adapters/pom.xml
COPY adapters/src/ adapters/src/

COPY domain/pom.xml domain/pom.xml
COPY domain/src/ domain/src/

COPY infrastructure/pom.xml infrastructure/pom.xml
COPY infrastructure/src/ infrastructure/src/

COPY usecase/pom.xml usecase/pom.xml
COPY usecase/src/ usecase/src/

COPY pom.xml .

# Building the application, skipping tests
RUN mvn clean package -DskipTests
# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copying the built JAR from the build stage to the runtime stage
COPY --from=build /app/infrastructure/target/*.jar app.jar
# Exposing the application port
EXPOSE 8080
# Entry command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]