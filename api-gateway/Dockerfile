# Use a base image with OpenJDK (Java 17 in this case)
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the entire project into the container (including the parent `build.gradle`)
COPY . .

# Install Gradle (if you are not using the wrapper, otherwise use the wrapper script)
RUN ./gradlew build --no-daemon

# Expose the port that the service will run on
EXPOSE 8761

# Run the service (e.g., Eureka service)
CMD ["java", "-jar", "build/libs/api-gateway-service.jar"]