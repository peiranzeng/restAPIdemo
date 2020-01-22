FROM openjdk:8
EXPOSE 8080

ADD target/dockerdemo.jar .

CMD ["java", "-jar", "dockerdemo.jar"]
