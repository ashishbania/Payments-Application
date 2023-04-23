FROM openjdk:11-jdk-slim
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ADD target/docker-demo.jar docker-demo.jar
ENTRYPOINT ["java","-jar","/app.jar"]
