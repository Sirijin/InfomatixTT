# Используем образ с Java 17 и на базе Alpine Linux
FROM alpine:latest
RUN apk update && apk add --no-cache openjdk17-jre

# Создаем директорию для приложения
RUN mkdir /app

# Копируем JAR-файл приложения в директорию /app в контейнере
COPY build/libs/ELibraryManager-0.0.1-SNAPSHOT.jar /app/ELibraryManager.jar

# Определяем рабочую директорию внутри контейнера
WORKDIR /app

# Устанавливаем переменную окружения для порта
ENV PORT 8080

# Запускаем приложение и передаем параметр для выполнения SQL-скрипта
CMD ["java", "-jar", "ELibraryManager.jar"]
