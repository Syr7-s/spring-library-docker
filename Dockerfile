FROM openjdk:8
ADD target/spring-docker-library.jar spring-docker-library.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-docker-library.jar"]