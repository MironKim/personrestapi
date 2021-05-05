###Инструкция:
####Импортировать проект в IntellijIDEA
* На экране выбора проекта нажать `Ctrl+Shift+A`
* Найти действие `Project from Existing Sources` 
* Выбрать папку с проектом (в корне должен находиться `pom.xml`) 
* Далее выбрать тип проекта `maven` 
####Сборка
* Выполнить сборку с помощью команды `mvn clean install`
* Или с помощью заготовленного runConfigurations - `personrestapi [clean,install]`
* Проверить что папка по пути `target/generated-sources/openapi/src/main/java` отмечена как `Generated sources root`.  
  Если нет, то `правой кнопкой мыши по папке` > `Mark Directory as` > `Generated sources root`
####Запуск
* Запустить проект можно выполнив main метод из класса `com.example.rest.PersonrestapiApplication` 
* Или с помощью заготовленного runConfigurations - `PersonrestapiApplication` 
---
###Для просмотра содержимого [H2 DB](http://localhost:8081/api/h2-console) `username=sa` `password=password`
###Для просмотра и проверки API [Swagger-UI](http://localhost:8081/api/swagger-ui.html) 

---
###Тестовое задание №1

* Описание: Сервер API (JSON HTTP API)
* Средства разработки: Java
* Framework: Spring boot
* База данных: H2 DB
* Протокол: HTTP, порт 8081

####Модель:
* Человек (поля: ID, фамилия, имя, отчество, дата рождения)

####Функционал (запросы):
* `POST /persons` (сохранение человека в БД, в body запроса должны передаваться все необходимые поля)
* `GET /persons/{id}` (получение человека из БД по ID)
* `POST /persons/search` (поиск людей в БД по дате рождения, в body запроса должен быть фильтр с min и max датой рождения)

####Обязательные требования:
* RESTful
* Все данные в формате JSON
* Документирование кода
* Unit-тесты
* Сборка с помощью mvn
* README-файл с подробной инструкцией по развертыванию
* Проект упаковать в zip-архив

