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


![image](https://user-images.githubusercontent.com/12966963/221984588-a4d9e51c-0542-41c7-8f3c-38bf8b7eb8f4.png)
