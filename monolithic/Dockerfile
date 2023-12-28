FROM docker.io/library/amazoncorretto:21-alpine-jdk AS builder

WORKDIR /build
COPY . /build
RUN ./gradlew build -x test

FROM docker.io/library/amazoncorretto:21-alpine-jdk

# maria
ENV POSTGRES_IP=postgres_ip
ENV POSTGRES_PORT=postgres_port
ENV POSTGRES_DB=postgres_db
ENV POSTGRES_USER=postgres_user
ENV POSTGRES_PASSWORD=postgres_password

# redis
ENV REDIS_PORT=redis_port

EXPOSE 8080

RUN mkdir -p /usr/local/bin
COPY --from=builder /build/build/libs/*.jar /usr/local/bin/app.jar

ENTRYPOINT ["java", "-jar", "/usr/local/bin/app.jar"]
