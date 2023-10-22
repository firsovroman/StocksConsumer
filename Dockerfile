FROM openjdk:11

# Копируем сборку StocksFetcher.jar в контейнер
COPY target/StocksFetcher.jar /home/StocksFetcher.jar

# запускаем сам сервис
CMD ["java", "-jar", "/home/StocksFetcher.jar"]