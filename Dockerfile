FROM alpine:latest
RUN apk update && apk add --no-cache openjdk17-jre

RUN mkdir /app

COPY build/libs/ELibraryManager-0.0.1-SNAPSHOT.jar /app/ELibraryManager.jar

WORKDIR /app

ENV PORT 8080

CMD ["java", "-jar", "ELibraryManager.jar"]
