# Use Maven image with JDK 18 for building
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/com/amazingJava/myFirstWebApp/MyFirstWebAppApplication.java /home/app/src/main/java/com/amazingJava/myFirstWebApp/MyFirstWebAppApplication.java
#RUN mvn -f /home/app/pom.xml clean package

COPY . /home/app
#RUN mvn -f /home/app/pom.xml clean package

# Use OpenJDK 21-slim for running the application
FROM openjdk:21-slim
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]




