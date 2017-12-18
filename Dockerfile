FROM openjdk:8
RUN mkdir -p /usr/app
WORKDIR /usr/app
ADD . /usr/app
CMD sh
#RUN ./gradlew
#ENV SERVER_PORT 80
#EXPOSE 80
#CMD ["java","-jar","build/libs/JsonFormatter-all-0.01.jar"]
