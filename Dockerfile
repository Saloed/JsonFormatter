FROM maven:3.3-jdk-8-onbuild
ENV SERVER_PORT 80
CMD ["java","-jar","/usr/src/app/target/JsonFormatter-0.01-jar-with-dependencies.jar"]
EXPOSE 80
