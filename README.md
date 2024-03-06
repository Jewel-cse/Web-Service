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
FROM openjdk:18.0-slim
COPY target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/app.jar"]
```

## Dockerfile - 2 - Build Jar File - Multi Stage
```
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:18.0-slim
EXPOSE 5000
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]

```

## Dockerfile - 3 - Caching

```
FROM maven:3.8.6-openjdk-18-slim AS build
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