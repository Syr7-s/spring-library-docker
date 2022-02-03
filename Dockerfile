FROM maven:3.8.4-jdk-8 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml -Dmaven.test.skip=true clean package

FROM openjdk:8-alpine
COPY --from=build /usr/src/app/target/spring-library-docker.jar /usr/app/spring-library-docker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/spring-library-docker.jar"]