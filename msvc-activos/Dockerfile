FROM openjdk:23-ea-17-jdk

WORKDIR /app

COPY ./target/msvc-activos-0.0.1-SNAPSHOT.jar .

EXPOSE 9003
ENTRYPOINT ["java", "-jar", "msvc-activos-0.0.1-SNAPSHOT.jar"]