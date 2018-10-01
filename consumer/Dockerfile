FROM openjdk:8-jdk-alpine
ADD . /
RUN ./gradlew clean assemble

FROM openjdk:8-jdk-alpine
COPY --from=0 /build/libs/consumer-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","app.jar"]
