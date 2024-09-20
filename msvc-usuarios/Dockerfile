FROM openjdk:17.0-jdk

WORKDIR /app

COPY ./target/msvc-usuarios-0.0.1-SNAPSHOT.jar .

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]
