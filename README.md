### Обзор

Данный проект представляет собой REST API для сервиса, аналогичного Pastebin.com. Сервис позволяет пользователям загружать куски текста/кода ("пасты") и получать на них короткие ссылки, которые можно отправить другим людям.

### Функциональность

• Создание пасты: 
  * Пользователь может загрузить пасту, указав:
    * Текст пасты
    * Срок доступности (life_time): 10 мин., 1 час, 3 часа, 1 день, 1 неделя, 1 месяц, без ограничения
    * Ограничение доступа:
      * public — доступна всем
      * unlisted — доступна только по ссылке
  * Сервис генерирует короткую ссылку вида http://my-awesome-pastebin.tld/{какой-то-рандомный-хэш}
• Получение пасты:
  * Пользователь может получить доступ к пасте по ее короткой ссылке.
  * Если срок доступности пасты истек, доступ к ней запрещен, даже для автора.
• Просмотр последних публичных паст: 
  * Пользователи могут получить информацию о последних 10 загруженных публичных пастах.

### Endpoints

#### Создание пасты:

• POST /my-awesome-pastebin.tld/registration

  * Request body:
    * body: Текст пасты (обязательно)
    * life_time: Срок доступности (необязательно, по умолчанию без ограничения)
    * status: Ограничение доступа (public, unlisted, по умолчанию unlisted)

  * Response body:
    * body: Текст пасты
    * life_time: Срок доступности
    * status: Ограничение доступа
    * message: Ссылка на пасту
#### Получение пасты:

• GET /my-awesome-pastebin.tld/{hash}

  * Parameters:
    * hash: Короткая ссылка на пасту

  * Response body:
    * body: Текст пасты
    * life_time: Срок доступности
    * status: Ограничение доступа

#### Получение последних публичных паст:

• GET /my-awesome-pastebin.tld

  * Response body:
    * pastes: Массив объектов с информацией о последних 10 публичных пастах (body, life_time, status)

### Техническая реализация

• Язык программирования: Java
• Фреймворк: Spring boot и соответствующие стартеры
• База данных: PostgreSql

### Установка и запуск

Есть несколько вариантов установки и запуска:
1) Установка и запуск в среде разработки intellij idea. Клонировать проект из github. Собрать проект с помощью мвен используя clean install.
Запустить проект в intellij idea задав в конфигурации запуска переменную окружения, url бд(JDBC_URL=jdbc:postgresql://localhost:5432/pastebox_db),
где pastebox_db - имя таблицы в бд) или используя java -jar pastebox.jar, также задав переменную окружения.
предварительно необходимо создать базу данных с любым названием(в дальнейшем будет необходимо задавать имя бд в переменных окружения при запуске) и роль
с именем pastebox и паролем pastebox.

2) Второй вариант, это запускать docker-compose в intellij idea, или просто из консоли используя команнду docker-compose up. Локально будет развёрнута бд и проект. Можно сразу приступать к работе с проектом.
Для docker-compose используется другой url: jdbc:postgresql://postgres:5432/pastebox_db.

3) И наконец третий способ скачать докер образ с моего docker hub и запустить его.
### Тестирование
Реализация тестирования ещё не закончена, на данный момент тестируется только сервисный слой приложения.
