FROM openjdk:17-jdk-slim
WORKDIR /app
COPY zipkin/zipkin-server-3.5.1-exec.jar /app/zipkin.jar
EXPOSE 9411
ENTRYPOINT ["java", "-jar", "/app/zipkin.jar"]