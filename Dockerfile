FROM gradle:alpine
ADD . /home/gradle
RUN gradle fatJar --stacktrace
ENV SERVER_PORT 8000
EXPOSE 8000
CMD ["java","-jar","build/libs/JsonFormatter-all-0.01.jar"]
