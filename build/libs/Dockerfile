FROM openjdk:11-jdk

RUN apt-get update && apt-get -y install sudo

#ARG JAR_FILE="build/libs/iampotato-*.jar"
#
#COPY ${JAR_FILE} app.jar

COPY iampotato-*.jar app.jar

ENV PROFILE dev

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "/app.jar"]