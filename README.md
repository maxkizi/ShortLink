# README #
Cервис для создания короткой ссылки.


Java версия: `11`
База данных: `Postgres`

Сборка проекта осуществляется с помощью maven
`mvn clean package`

Запуск проекта осущестляется при помощи `docker-compose up --build`

Приложение состоит из двух микросервисов: Authentication Service и Base Service

AUTHENTICATION SERVICE:
1) Предоставляет рест апи для регистрации пользователя и для получения jwt токена
2) При старте приложения в БД загружаются два пользователя c ролями: `ROLE_ADMIN (username:admin_user, password:admin)`
и `ROLE_GUEST (username:guest_user, password:guest)`
3) Пользователь с ролью `ROLE_ADMIN` имеет доступ ко всем конечным точкам Base Service
4) Пользователь с ролью `ROLE_GUEST` имеет доступ для получения короткой ссылки, использовния её для перехода на полную ссылку
5) Доступ к Base Service осуществляется посредством получения JWT токена в Authentication Service и добавления его к хедеру
Authorization с префиксом Bearer в запросе
6) Время жизни jwt токена регулируется параметром `expirationTokenMinutes` в application.yaml
7) Swagger файл(open-api): `shortlink-authentication-service/src/main/resources/open_api/authentication-service.yaml`
    

BASE SERVICE:   
1) Предоставляет апи для получения короткой ссылки по полной ссылке(`ROLE_ADMIN`,`ROLE_GUEST`), 
переход на полную ссылку через короткую(`ROLE_ADMIN`, `ROLE_GUEST`), удаление короткой ссылки(`ROLE_ADMIN`),
получение статистики информации о колличестве переходов по ссылке(`ROLE_ADMIN`)
2) Время жизни короткой ссылки регулируется параметром, `expirationLinkMinutes` в application.yaml
3) Swagger файл(open-api): `shortlink-base-service/src/main/resources/open_api/short-link-service.yaml`

