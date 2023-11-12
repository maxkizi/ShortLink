FROM openjdk:11-jdk
ARG SHORTLINK_JAR=target/*.jar
COPY ${SHORTLINK_JAR}  shortlink.jar
COPY target/classes/application-prod.yaml app.yaml
ENTRYPOINT ["java", "-jar", "/shortlink.jar"]