FROM openjdk:8
ADD target/spring-library-docker.jar spring-library-docker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-library-docker.jar"]