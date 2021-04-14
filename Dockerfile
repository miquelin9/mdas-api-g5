FROM openjdk:16-ea-11-alpine

WORKDIR /

COPY /build/sensor-api-dev.jar /home/demo.jar

EXPOSE 8081

CMD ["java", "-jar", "/home/demo.jar"]