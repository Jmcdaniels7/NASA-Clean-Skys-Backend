# Stage 1: Build the app using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the app with a lightweight JRE
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Set the port Google Cloud will expose
EXPOSE 8080
# Default command
ENTRYPOINT ["java", "-jar", "app.jar"]