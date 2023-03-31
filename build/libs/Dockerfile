FROM openjdk:11-jdk

RUN apt-get update && apt-get -y install sudo

COPY iampotato-*.jar app.jar

ENV PROFILE dev

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "/app.jar"]