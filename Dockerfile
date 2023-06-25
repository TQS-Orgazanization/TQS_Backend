FROM maven:3-eclipse-temurin-17 as BUILD

COPY PickupPointBackEnd /usr/src/app
RUN mvn --batch-mode -f /usr/src/app/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre
ENV PORT 80
EXPOSE 80
COPY --from=BUILD /usr/src/app/target/artifact.jar /opt/target
WORKDIR /opt/target

CMD ["java","-Xms2g","-Xmx4g","-jar","/artifact.jar"]
