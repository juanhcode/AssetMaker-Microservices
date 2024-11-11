FROM openjdk:23-ea-17-jdk

WORKDIR /app

COPY ./target/msvc-portafolios-0.0.1-SNAPSHOT.jar .

EXPOSE 9002

ENTRYPOINT ["java", "-jar", "msvc-portafolios-0.0.1-SNAPSHOT.jar"]
