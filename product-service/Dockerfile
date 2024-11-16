# Use a base image with OpenJDK 17
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and related files for caching
COPY gradlew .
COPY gradle/ gradle/

# Add execution permissions to the Gradle wrapper
RUN chmod +x ./gradlew

# Copy the rest of the project files to the container
COPY .m2 /root/.m2
COPY . .

# Build the project using the Gradle wrapper (runs in the /app directory)
RUN ./gradlew build --no-daemon

# Expose the port the config server will run on (default for Eureka is 8761)
EXPOSE 7071

# Run the application using the built JAR file (replace with your actual JAR name if necessary)
CMD ["java", "-jar", "build/libs/product-service-1.0-SNAPSHOT.jar"]
