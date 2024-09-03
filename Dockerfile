FROM gradle:8.8-jdk17 AS build
WORKDIR /app
COPY --chown=gradle:gradle .. /app
RUN gradle build --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 3030
ENTRYPOINT ["java", "-jar", "app.jar"]