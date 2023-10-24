FROM bellsoft/liberica-openjdk-alpine:17

EXPOSE 8080
ARG JAVA_OPTS

WORKDIR /app
ENV TZ "UTC"

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

ENTRYPOINT exec java $JAVA_OPTS \
    -Duser.language=ru \
    -Duser.region=RU \
    -Duser.timezone=${TZ} \
    -jar app.jar

COPY build/libs/kotlin-backend-app-ktor-all.jar app.jar
