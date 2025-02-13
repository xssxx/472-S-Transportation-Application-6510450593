FROM openjdk:21-jdk-slim

WORKDIR /app

COPY ./backend /app

RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

RUN ./mvnw dependency:go-offline

CMD ["./mvnw", "spring-boot:run"]
