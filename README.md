# StocksFetcher 

Микросервис, который работает со сторонним API (https://iexcloud.io/docs/) и обеспечивает сбор и анализ информации о стоимости акций компаний, в потокобезопасном режиме.



Рис.1 - Диаграмма последовательности.

![alt text](https://github.com/firsovroman/StocksFetcher/blob/master/.doc/diagrams/sequins-diagram.png)



Рис.2 - Схема используемого паттерна многопоточной обработки.

![alt text](https://github.com/firsovroman/StocksFetcher/blob/master/.doc/diagrams/multiThread_pattern.png)




# Конфигурационные файлы и свойства

| Файл | Свойство | Описание |
|------------|------------|------------|
| apperate.properties  | apperate-client.url  | целевой АПИ  |
| apperate.properties   | apperate-client.token   | Токен для подключения. Можно получить в ЛК после регистрации. (https://iexcloud.io/cloud-login?r=https%3A%2F%2Fiexcloud.io%2Fconsole%2Fhome#/)  |
| processor.properties  | processor.scheduleCompaniesReaderIntervalMillis  | Интервал для запуска джобы вычитывания компаний.   |
| processor.properties   | processor.scheduleStocksReaderIntervalMillis   | Интервал для запуска джобы получения информации по акциям компаний.  |
| processor.properties   | processor.scheduleReporterIntervalMillis   | Интервал для запуска джобы вывода полезной информации по стоимости акций компаний.  |


# Установка и настройка на локальной машине разработчика (dev).
На локальной машине разработчика потребуется наличие запущенной PostgreSQL, а так же JDK 11.

1. Клонируйте репозиторий на свой локальный компьютер
2. В конфигурационном файле <b>src/main/resources/apperate.properties</b> задайте свойство <b>apperate-client.token </b> введите свой публичный API-токен
3. Запустите скрип <b>init.sql</b> на локальной PostgreSQL
4. С помошью maven соберите проект. (ВАЖНО! Соберите с использованием профиля сборки <b>dev</b> )
5. Запускайте <b>target/StocksFetcher.jar</b> любым удобным сопсобом

# Установка и настройка в контейнере (prod).

Для запуска потребуется наличие контейнеризатора приложений Docker

1. Клонируйте репозиторий на свой локальный компьютер
2. ...
