# TaskApp
1. Склонировать проект.
2. запустить docker-compose командой:
docker-compose up --build.
3. подключиться к БД:
   jdbc:postgresql://localhost:5432/
   логин: postgres
   password: admin
4. выполнить скриты по пути:
~/hh/TaskApp/src/main/resources/scripts -- (установка расширения для uuid)создание таблицы
5. зайти через браузер на localhost:3000

Функциональность:
Можно создавать/изменять/удалять задачи для выполнения.
Для задач можно указать название и описание.