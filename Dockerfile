FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /jewelry-manager-api-app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /jewelry-manager-api-app
COPY --from=build /jewelry-manager-api-app/target/*.jar jewelry-manager-api-app.jar

ENTRYPOINT ["java", "-jar", "jewelry-manager-api-app.jar"]