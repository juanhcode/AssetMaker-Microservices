FROM openjdk:23-ea-17-jdk

WORKDIR /app

COPY ./target/msvc-gateway-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "msvc-gateway-0.0.1-SNAPSHOT.jar"]