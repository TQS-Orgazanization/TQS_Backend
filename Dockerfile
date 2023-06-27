FROM maven:3-eclipse-temurin-17 as BUILD

COPY PickupPointBackEnd /app
WORKDIR /app
RUN mvn --batch-mode clean install package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=BUILD /app/target/artifact.jar /app/artifact.jar

CMD ["java", "-Xms2g", "-Xmx4g", "-jar", "/app/artifact.jar"]

