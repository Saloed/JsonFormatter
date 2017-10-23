FROM maven:3.3-jdk-8-onbuild
FROM java:8
COPY --from=0 /usr/src/app/target/JsonFormatter-0.01-jar-with-dependencies.jar /opt/JsonFormatter-0.01-jar-with-dependencies.jar
CMD ["java","-jar","/opt/JsonFormatter-0.01-jar-with-dependencies.jar"]
EXPOSE 80
