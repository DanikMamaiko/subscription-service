FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar subscribe-app.jar
ENTRYPOINT ["sh", "-c", "sleep 20; java -jar /app/subscribe-app.jar"]