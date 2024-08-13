FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY target/pastebox.jar /app/pastebox.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pastebox.jar"]