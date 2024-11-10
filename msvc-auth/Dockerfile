FROM openjdk:23-ea-17-jdk

WORKDIR /app

COPY ./target/msvc-auth-0.0.1-SNAPSHOT.jar .

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "msvc-auth-0.0.1-SNAPSHOT.jar"]