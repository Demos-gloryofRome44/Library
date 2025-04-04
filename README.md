### 🔩 **Cтек**

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-6DB33F?logo=spring-boot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-316192?logo=postgresql)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-24.0-2496ED?logo=docker)](https://www.docker.com/)
[![Liquibase](https://img.shields.io/badge/Liquibase-4.23-2962FF?logo=liquibase)](https://www.liquibase.org/)
[![Spring MVC](https://img.shields.io/badge/Spring%20MVC-6.0-6DB33F?logo=spring)](https://docs.spring.io/spring-framework/reference/web.html)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.4.4-005F0F?logo=thymeleaf)](https://www.thymeleaf.org/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.36-47a1c6?logo=lombok)](https://projectlombok.org/)
[![JDBC](https://img.shields.io/badge/JDBC%20Template-3.4.4-red)](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html)
[![REST API](https://img.shields.io/badge/REST%20API-brightgreen)](https://restfulapi.net/)
[![Maven](https://img.shields.io/badge/Apache%20Maven-3.9.5-C71A36?logo=apache-maven)](https://maven.apache.org/)

# 📚 Библиотечная система управления книгами

**Система учета книг с назначением ответственных лиц** - Spring Boot приложение для управления библиотекой с возможностью закрепления книг за конкретными читателями.

## 🌟 Ключевая функциональность

### 📖 Управление книгами
- Полный CRUD для книг (добавление/редактирование/удаление)
- Список всех книг в библиотеке
- Просмотр текущего статуса книги (доступна/на руках)

### 👥 Управление читателями
- Полный CRUD для читателей (добавление/редактирование/удаление)
- Список всех зарегистрированных пользователей
- Просмотр текущего статуса читателя сколько книг на руках

### 🔗 Назначение книг
- Закрепление книги за читателем
- Автоматическое обновление статуса книги при назначении
- Возврат книг в библиотеку

## 🚀 Установка

### 1. Клонируйте репозиторий

Склонируйте репозиторий с игрой с помощью следующей команды:

```bash
git clone https://github.com/Demos-gloryofRome44/Library.git
```

### 2. Запуск через Docker Compose

```bash
docker-compose up
```

### 3. Запуск приложения и доступ к нему 
После запуска перейдите в браузер:

```bash
http://localhost:8080
```
