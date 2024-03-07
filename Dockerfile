# Use Maven image with JDK 18 for building
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app
COPY . /home/app

# Use OpenJDK 21-slim for running the application
FROM openjdk:21-slim
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]




