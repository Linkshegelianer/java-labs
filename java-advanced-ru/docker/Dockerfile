FROM gradle:7.4.0-jdk17
ARG JAR_FILE=app/build/libs/*.jar
COPY ${JAR_FILE} simple-application.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "simple-application.jar"]
