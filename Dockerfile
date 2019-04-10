FROM openjdk:8-jre-alpine
COPY target/user-0.0.1-SNAPSHOT.jar /application.jar
CMD ["/usr/bin/java", "-jar", "/application.jar"]