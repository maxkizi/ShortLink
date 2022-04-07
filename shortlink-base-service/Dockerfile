FROM openjdk:11-jdk
ARG SHORTLINK_JAR=target/*.jar
COPY ${SHORTLINK_JAR}  shortlink.jar
ENTRYPOINT ["java", "-jar", "/shortlink.jar"]