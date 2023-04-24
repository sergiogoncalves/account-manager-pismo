FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/account-manager.jar
COPY ${JAR_FILE} account-manager.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/account-manager.jar"]