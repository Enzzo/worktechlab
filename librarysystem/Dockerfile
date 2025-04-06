FROM maven:3.8.7-openjdk-18-slim as build
LABEL maintainer="Sergey Vasilev <vasilevnogliki@yandex.ru>"
WORKDIR /app
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=tools -jar app.jar extract --layers --launcher

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/app/dependencies/ ./
COPY --from=build /app/app/application/ ./
COPY --from=build /app/app/snapshot-dependencies/ ./
COPY --from=build /app/app/spring-boot-loader/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]