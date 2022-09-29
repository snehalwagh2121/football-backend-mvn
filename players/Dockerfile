FROM openjdk:17-alpine
EXPOSE 9002
ADD target/players-0.0.1-SNAPSHOT.jar players-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "players-0.0.1-SNAPSHOT.jar"]