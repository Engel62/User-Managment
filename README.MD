# User Management REST API

REST API приложение для управления пользователями с использованием Spring Boot 3 и PostgreSQL.

## 🚀 Технологии

- **Java 17**
- **Spring Boot 3.2.0**
- **PostgreSQL 15**
- **Liquibase** (миграции базы данных)
- **Spring Data JPA**
- **Spring Validation**
- **Spring Cache** (Caffeine)

## 📋 Функциональность

- ✅ Добавление нового пользователя
- ✅ Получение информации о пользователе по UUID
- ✅ Обновление информации о пользователе
- ✅ Удаление пользователя по UUID
- ✅ Валидация входных данных
- ✅ Обработка ошибок через @ControllerAdvice
- ✅ Кэширование данных
- ✅ Миграции базы данных

## 🚀 Быстрый старт

### 1. Клонирование и сборка
```git clone <your-repo-url>
cd user-api  
```   
mvn clean package
2. Настройка базы данных

# Создайте базу данных PostgreSQL
```createdb userdb```

# Или выполните в psql:
# CREATE DATABASE userdb;
3. Запуск приложения

```mvn spring-boot:run```
Приложение будет доступно по адресу: http://localhost:8080

📊 База данных
Схема таблиц
sql
```CREATE TABLE roles (
    id UUID PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
    id UUID PRIMARY KEY,
    fio VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) NOT NULL UNIQUE,
    avatar TEXT,
    role_id UUID NOT NULL REFERENCES roles(id)
);
````
Предустановленные данные
Роль: USER (UUID: a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11)

Роль: ADMIN (UUID: b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12)

📡 API Endpoints
1. Создание пользователя
POST /api/createNewUser

Тело запроса:
````{
    "fio": "Иванов Иван Иванович",
    "phoneNumber": "+79161234567",
    "avatar": "https://example.com/avatar.jpg",
    "roleName": "USER"
}
````
Ответ:
```{
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "fio": "Иванов Иван Иванович",
    "phoneNumber": "+79161234567",
    "avatar": "https://example.com/avatar.jpg",
    "roleName": "USER"
}
```
2. Получение пользователя
GET /api/users?userID=123e4567-e89b-12d3-a456-426614174000
Ответ:
```
{
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "fio": "Иванов Иван Иванович",
    "phoneNumber": "+79161234567",
    "avatar": "https://example.com/avatar.jpg",
    "roleName": "USER"
}
```
3. Обновление пользователя
PUT /api/userDetailsUpdate?userID=123e4567-e89b-12d3-a456-426614174000
Тело запроса:
```{
    "fio": "Петров Петр Петрович",
    "phoneNumber": "+79169876543",
    "avatar": "https://example.com/new-avatar.jpg",
    "roleName": "ADMIN"
}
```
4. Удаление пользователя
DELETE /api/users?userID=123e4567-e89b-12d3-a456-426614174000

Ответ: 204 No Content

⚙️ Конфигурация
Настройки базы данных (application.properties)

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/userdb
spring.datasource.username=postgres
spring.datasource.password=password
```
# JPA
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Liquibase
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.yaml

# Cache
spring.cache.type=caffeine
```
🐛 Обработка ошибок
Приложение возвращает структурированные ошибки:

```
{
    "error": "User not found with id: 123e4567-e89b-12d3-a456-426614174000"
}
```
Возможные ошибки:

400 Bad Request - невалидные данные

404 Not Found - пользователь не найден

409 Conflict - телефон уже существует

500 Internal Server Error - серверная ошибка

🧪 Тестирование
Запуск тестов
```
mvn test
```
Примеры curl запросов
Создание пользователя:

```
curl -X POST http://localhost:8080/api/createNewUser \
  -H "Content-Type: application/json" \
  -d '{
    "fio": "Иванов Иван Иванович",
    "phoneNumber": "+79161234567",
    "avatar": "https://example.com/avatar.jpg",
    "roleName": "USER"
  }'
  ```
Получение пользователя:
```curl -X GET "http://localhost:8080/api/users?userID=123e4567-e89b-12d3-a456-426614174000"```
📦 Зависимости
Основные зависимости:

Spring Boot Starter Web

Spring Data JPA

PostgreSQL Driver

Liquibase Core

Spring Validation

Spring Cache

🔧 Разработка
Настройка среды разработки
Установите Java 17

Установите Maven

Установите PostgreSQL

Настройте IDE (IntelliJ IDEA рекомендуется)

Работа с миграциями
Миграции Liquibase находятся в ```src/main/resources/db/changelog/```

📝 Принципы разработки
SOLID принципы

MVC архитектура

REST best practices

Валидация на всех уровнях

Единообразная обработка ошибок

Кэширование для производительности
