FROM gradle:alpine
ADD . app
RUN app/gradlew
ENV SERVER_PORT 8000
EXPOSE 8000
CMD ["java","-jar","app/build/libs/JsonFormatter-all-0.01.jar"]
