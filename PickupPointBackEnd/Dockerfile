FROM openjdk:17-alpine
ADD target/artifact.jar /
CMD ["java","-Xms2g","-Xmx4g","-jar","/artifact.jar"]