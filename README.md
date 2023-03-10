# TaskApp
1. Склонировать проект.
2. запустить докер командой: docker-compose up
3. зайти через браузер на localhost:3000

Функциональность:
Можно создавать/изменять/удалять задачи для выполнения.
Для задач можно указать название и описание. 
Появилась функция отмечать задачу выполненной - кнопка "Completed" - после нажатия, задача
считается выполненной и пропадает из списка задач.

Стек:
фронт на реакте,
бэк: springboot
бд: postgres
все упаковоно в docker-compose. Из фронта запросы проксируются на endpointы в беке.
scripts -- (установка расширения для uuid)создание таблицы

![image](https://user-images.githubusercontent.com/12966963/224093121-1120f0fb-8f11-4753-b991-c01d87879a88.png)
