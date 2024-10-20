FROM openjdk:23-ea-17-jdk

WORKDIR /app

COPY ./target/msvc-usuarios-0.0.1-SNAPSHOT.jar .

EXPOSE 9001

ENTRYPOINT ["java", "-jar", "msvc-usuarios-0.0.1-SNAPSHOT.jar"]
