FROM sbtscala/scala-sbt:eclipse-temurin-18.0.2_1.8.0_3.2.0
WORKDIR /app
ENV HTTP_HOST="0.0.0.0"
COPY build.sbt /app
RUN ["sbt", "compile"]
COPY .. /app
EXPOSE 8080
CMD ["sbt", "run"]