# TaskApp
1. Склонировать проект.
2. сбилдить проект
командой mvn clean package
3. запустить docker-compose командой:
docker-compose up --build.
4. подключиться к БД:
   jdbc:postgresql://localhost:5432/
   логин: postgres
   password: admin
5. выполнить скриты по пути:
~/hh/TaskApp/src/main/resources/scripts -- (установка расширения для uuid)создание таблицы
6. зайти через браузер на localhost:3000

Функциональность:
Можно создавать/изменять/удалять задачи для выполнения.
Для задач можно указать название и описание.

Стек:
фронт на реакте,
бэк: springboot
бд: postgres
все упаковоно в docker-compose. Из фронта запросы проксируются на endpointы в беке.

![image](https://user-images.githubusercontent.com/12966963/221934393-2d0ba6d4-b00d-4b2a-b7a4-6910efddf0dc.png)