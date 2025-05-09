FROM openjdk:17
WORKDIR /app
COPY target/subscription-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9010
ENTRYPOINT ["java", "-jar", "/app.jar"]
