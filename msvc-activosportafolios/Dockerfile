FROM openjdk:23-ea-17-jdk

WORKDIR /app

COPY ./target/msvc-activosportafolios-0.0.1-SNAPSHOT.jar .

EXPOSE 9004

ENTRYPOINT ["java", "-jar", "msvc-activosportafolios-0.0.1-SNAPSHOT.jar"]
