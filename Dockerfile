# ==========================================
# Build Stage
# ==========================================
# Use a Maven image with Java 21 to build the project
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy your pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application (skipping tests to speed up deployment)
RUN mvn clean package -DskipTests

# ==========================================
# Run Stage
# ==========================================
# Use a lighter JRE image to run the application
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the built .jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Run the application.
# Render passes a $PORT environment variable; this ensures Spring Boot uses it.
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar app.jar"]