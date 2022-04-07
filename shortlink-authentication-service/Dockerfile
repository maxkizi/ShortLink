FROM openjdk:11-jdk
ARG AUTHSERVICE_JAR=target/*.jar
COPY ${AUTHSERVICE_JAR}  authservice.jar
ENTRYPOINT ["java", "-jar", "/authservice.jar"]