# Dockerfile
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
COPY --from=build /*.jar taco.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "taco.jar"]
