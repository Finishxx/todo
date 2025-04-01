FROM eclipse-temurin:23-jdk


WORKDIR /app

COPY gradlew gradlew.bat ./
COPY gradle gradle
RUN chmod +x gradlew

COPY . .

EXPOSE 8080

CMD ["./gradlew", "bootRun"]
