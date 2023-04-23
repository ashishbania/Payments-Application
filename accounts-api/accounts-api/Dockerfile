FROM openjdk:11-jdk-slim
EXPOSE 8080
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ADD target/accounts-api.jar accounts-api.jar
ENTRYPOINT ["java","-jar","/accounts-api.jar"]
