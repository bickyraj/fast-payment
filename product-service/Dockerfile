# Use a base image with OpenJDK 17 for building
FROM openjdk:17-jdk-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and related files for caching
COPY gradlew .
COPY gradle/ gradle/

# Add execution permissions to the Gradle wrapper
RUN chmod +x ./gradlew

# Copy the rest of the project files to the container
COPY . .

# Build the project using the Gradle wrapper (runs in the /app directory)
RUN ./gradlew clean build --no-daemon

# --------------------------------------------------

# Use a smaller runtime image to run the JAR
FROM openjdk:17-jdk-slim

# Set the working directory for the runtime
WORKDIR /app

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/build/libs/*.jar ./app.jar

# Expose the port the service will run on (update to match your service)
EXPOSE 7071

# Run the application using the copied JAR file
CMD ["java", "-jar", "/app/app.jar"]
