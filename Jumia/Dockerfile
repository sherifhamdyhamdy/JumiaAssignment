FROM adoptopenjdk/openjdk11:alpine-jre
COPY sample.db .
EXPOSE 8080
ARG JAR_FILE=target/Jumia-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
