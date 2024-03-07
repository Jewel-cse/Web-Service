# Dockerfile Examples

## Docker commands
* docker --version
* docker container ls
* if not ok go to docker desktop and fixed then...
* Be careful with this command ....version space dot
```
docker build -t jewel/hello-world-docker:v1 .
```
## To check docker image list
```
docker image list
```
## To run that image
``` 
    docker container run -d -p 5000:5000 jewel/hello-world-docker:v1
```
## Dockerfile - 1 - Creating Docker Images

```
FROM openjdk:21-slim
COPY target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/app.jar"]
```

# Image creation Part-2
build the jar file in local is not recommended , we want to build jar in the container and run it
## Dockerfile - 2 - Build Jar File - Multi Stage
```
# Use Maven image with JDK 18 for building
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app
COPY . /home/app

# Use OpenJDK 21-slim for running the application
FROM openjdk:21-slim
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

```

## Dockerfile - 3 - Caching
If there are any change occur ,then its build from the start.
so we need to chache them and build the specific changes 
```
FROM maven:3.9.6-openjdk-21-slim AS build
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/com/amazingJava/myFirstWebApp/MyFirstWebAppApplication.java    /home/app/src/main/java/com/amazingJava/myFirstWebApp/MyFirstWebAppApplication.java
RUN mvn -f /home/app/pom.xml clean package

COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:18.0-slim
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
```