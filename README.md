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
На локальной машине разработчика потребуется наличие запущенной MySQL, а так же JDK 8.

1. Клонируйте репозиторий на свой локальный компьютер
2. ...

# Установка и настройка в контейнере (prod).

Для запуска потребуется наличие контейнеризатора приложений Docker

1. Клонируйте репозиторий на свой локальный компьютер
2. ...
