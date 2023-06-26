FROM maven:3-eclipse-temurin-17 as BUILD

COPY PickupPointBackEnd /
RUN mvn --batch-mode -f /pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /target

CMD ["java","-Xms2g","-Xmx4g","-jar","/artifact.jar"]
