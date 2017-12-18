FROM openjdk:8
RUN wget -q https://services.gradle.org/distributions/gradle-3.3-bin.zip \
    && unzip gradle-3.3-bin.zip -d /opt \
    && rm gradle-3.3-bin.zip
ENV GRADLE_HOME /opt/gradle-3.3
ENV PATH $PATH:/opt/gradle-3.3/bin
RUN mkdir -p /usr/app
WORKDIR /usr/app
ADD . /usr/app
RUN ./gradlew
ENV SERVER_PORT 80
EXPOSE 80
CMD ["java","-jar","build/libs/JsonFormatter-all-0.01.jar"]
