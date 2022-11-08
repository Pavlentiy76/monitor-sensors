FROM adoptopenjdk/openjdk11:latest
ENV APP_HOME = /app
WORKDIR = $APP_HOME
EXPOSE 8080
COPY target/monitor-sensors-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]