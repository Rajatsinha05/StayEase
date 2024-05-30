# Use the Maven image to build the project
FROM gradle:7.3.3-jdk17 AS builder

# Set the working directory
WORKDIR /app

# Copy the Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY gradle.properties .

# Copy the source code
COPY src/ src/

# Build the application
RUN gradle build --no-daemon

# Use the Java 17 image for the runtime environment
FROM adoptopenjdk/openjdk17:alpine-jre

# Set environment variables
ARG MYSQL_DBNAME
ARG MYSQL_USERNAME
ARG MYSQL_PASSWORD
ARG MYSQL_URL
ARG MYSQL_PORT

# Set volume
VOLUME /tmp

# Set working directory
WORKDIR /app


# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port
EXPOSE 8090

# Run the application
CMD ["java", "-jar", "app.jar"]
