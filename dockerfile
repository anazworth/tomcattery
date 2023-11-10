FROM eclipse-temurin:17 as builder

WORKDIR /app

COPY . .

RUN ./gradlew build --no-daemon

FROM tomcat:10.0-jre17-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

RUN mkdir /usr/local/tomcat/app

COPY --from=builder /app/build/libs/*.war /usr/local/tomcat/webapps/app.war

EXPOSE 8080

CMD ["catalina.sh", "run"]