# Jewelry Manager API
[Russian version](README.ru.md) | [Английская версия](README.md)

## Описание проекта

API менеджера ювелирных изделий — это серверное решение для управления ювелирными изделиями и драгоценными металлами клиентов, реализующее функционал виртуального сейфа. Система предоставляет надежный инструментарий для отслеживания и контроля ценностей с генерацией уникального идентификатора (UIN) и полной историей операций.

– Централизованное управление клиентскими данными
– Учет ювелирных изделий и драгоценных металлов
– Генерация уникального идентификационного номера (УИН)
– Полный аудит операций с историей изменений
– RESTful API для интеграции с внешними системами

## Технологический стек

- **Язык**: Java 17
- **Фреймворк**: Spring Boot 3.x  
- **База данных**: PostgreSQL  
- **Система сборки**: Maven  
- **Документация API**: Swagger/OpenAPI  
- **Кэширование**: Spring Cache  
- **Тестирование**: Postman, JUnit 5, Mockito  

## Системные сущности

### Основные модели данных:

1. **Client** - информация о клиенте
    - Персональные данные (имя, фамилия, пол)
    - Страна происхождения
    - Связанный УИН 

2. **УИН** - уникальный идентификационный номер
    - Связь с клиентом 1:1
    - Список Ювелирные изделия
    - Список драгоценных металлов

3. **Ювелирные изделия** - ювелирное изделие
    - Название, описание
    - Вес, материал
    - Дата создания/обновления

4. **Драгоценный металл** - драгоценный металл
    - Тип металла, чистота
    - Форма, вес
    - Дата создания/обновления

## Конечные точки запросов API

### Авторизация
- `POST /api/v1/auth/signup` - регистрация
- `POST /api/v1/auth/login` - авторизация

### Клиенты
- `GET /api/v1/clients` - все клиенты  
- `PUT /api/v1/clients` - создать нового клиента
- `PATCH /api/v1/clients` - обновить клиента  
- `DELETE /api/v1/clients/{id}` - удалить клиента  

### UIN
- `GET /api/v1/client/uin?id={clientId}` - получить UIN клиента
- `PUT /api/v1/client/{clientId}/uin` - создать UIN для клиента
- `GET /api/v1/client/{uin}/uin` - получить полную информацию о клиенте по UIN  

### Ювелирные изделия
- `GET /api/v1/jewelries` - все ювелирные изделия  
- `PUT /api/v1/jewelries` - создать новое ювелирное изделие  
- `PATCH /api/v1/jewelries` - обновить ювелирное изделие
- `DELETE /api/v1/jewelries/{id}` - удалить ювелирное изделие

### Драгоценные металлы
- `GET /api/v1/metals` - все драгоценные металлы  
- `PUT /api/v1/metals` - создать драгоценный металл  
- `PATCH /api/v1/metals` - обновить драгоценный металл  
- `DELETE /api/v1/metals/{id}` - удалить драгоценный металл  

### История
- `GET /api/v1/history?uin={uin}` - вывести историю
- `POST /api/v1/history/update` - обновить историю
- `POST /api/v1/history/evict` - очистить кэш истории

## Запуск проекта

## API документация
После запуска приложения:  
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`  
- OpenAPI (JSON): `http://localhost:8080/v3/api-docs`  

**Пример интерфейса в Swagger:**
![Screenshot 2025-06-14 122135](https://github.com/user-attachments/assets/766b0f48-1413-45db-8b4f-2a5ddaf7511c)  
![Screenshot 2025-06-14 122230](https://github.com/user-attachments/assets/c55ff545-d1fc-4951-8948-925310f1866a)  
![Screenshot 2025-06-14 122156](https://github.com/user-attachments/assets/f78f1557-1cad-4188-b392-4060a20dec87)  
![Screenshot 2025-06-14 122147](https://github.com/user-attachments/assets/37a0ba4b-d5b3-4b41-811e-1982c7c95f24)  
![Screenshot 2025-06-14 122239](https://github.com/user-attachments/assets/4edaadf8-1a43-4a07-8291-8f55bb113244)  
![Screenshot 2025-06-14 122250](https://github.com/user-attachments/assets/2a60cbd2-05ea-4534-8823-3ea30b329f9c)  

## Примеры запросов Examples (Postman)
## Пример регистрации и аутентификации пользователя
**1. Регистрация пользователя:**
<img width="1894" height="436" alt="Снимок экрана 2025-08-07 170106" src="https://github.com/user-attachments/assets/e9a6cf3b-ac40-4cc6-8888-1c7ff21c3fa4" />
<img width="859" height="167" alt="Снимок экрана 2025-08-07 170113" src="https://github.com/user-attachments/assets/2d853a6d-64d7-4516-ae60-89070745f48d" />

**2. Аутентификация пользователя:**
<img width="1887" height="386" alt="Снимок экрана 2025-08-07 170336" src="https://github.com/user-attachments/assets/d577f7c9-f7d9-4a14-80a6-882879011373" />
<img width="388" height="100" alt="Снимок экрана 2025-08-07 170342" src="https://github.com/user-attachments/assets/b4466c2f-f9e5-4168-899c-38a563e20952" />

## Примеры запросов при работе с клиентами
**1. Создание клиента:**  
![Снимок экрана 2025-06-14 123432](https://github.com/user-attachments/assets/5d438fec-b4d6-41e2-ad58-8d0147ed6930)
![Снимок экрана 2025-06-14 123439](https://github.com/user-attachments/assets/168ec4e3-d17c-4aa3-84fc-bc4d88881f63)

**2. Редактировать клиента:**  
![Снимок экрана 2025-06-14 125335](https://github.com/user-attachments/assets/3675cb64-da4f-418d-8b25-39e0667c8aeb)
![Снимок экрана 2025-06-14 125342](https://github.com/user-attachments/assets/ed81f3c5-0a5e-452e-b767-1d856710ac91)

**3. Все клиенты:**  
![Снимок экрана 2025-06-14 124922](https://github.com/user-attachments/assets/17200906-16af-4639-a00e-f5d38c177f84)

**4. Удалить клиента:**  
![Снимок экрана 2025-06-14 124859](https://github.com/user-attachments/assets/c30180d3-7bf8-412f-a615-07f72f8be92a)

## Пример запросов при работе с UIN

**1. Создать UIN по ID:**  
![Снимок экрана 2025-06-14 123701](https://github.com/user-attachments/assets/5e04720c-a334-41d6-8b8e-b84caf57bfb8)
![Снимок экрана 2025-06-14 123711](https://github.com/user-attachments/assets/87610eb2-f48c-4951-864d-7de9f6f6bdfb)

**2. Получение UIN по ID:**  
![Снимок экрана 2025-06-14 123819](https://github.com/user-attachments/assets/5d230454-9559-46da-88d4-7d5879dcedf4)
![Снимок экрана 2025-06-14 123837](https://github.com/user-attachments/assets/41f66d3d-03a9-476b-a179-96fef302bef4)

**3. Получение полной информации о клиенте по UIN:**  
![Снимок экрана 2025-06-14 125706](https://github.com/user-attachments/assets/c3e58c68-f7ef-49a7-8c80-b009f0d8e724)
![Снимок экрана 2025-06-14 125745](https://github.com/user-attachments/assets/9a8e53ec-e196-494c-a913-8ff681a4dacf)


## Пример запросов при работе с ювелирными изделиями
**1. Создание украшения:**  
![Снимок экрана 2025-06-14 124143](https://github.com/user-attachments/assets/ddbca0cd-9705-440e-9f17-4430088c6e76)
![Снимок экрана 2025-06-14 124150](https://github.com/user-attachments/assets/5edfabad-8127-47fd-80c8-9cf058fc4e42)

**2. Редактировать украшение:**  
![Снимок экрана 2025-06-14 124525](https://github.com/user-attachments/assets/a1aa3b63-9131-4801-9669-5679f420172d)
![Снимок экрана 2025-06-14 124536](https://github.com/user-attachments/assets/f2ac952e-5820-480b-827b-c24300deb020)

**3. Все украшения:**  
![Снимок экрана 2025-06-14 130835](https://github.com/user-attachments/assets/7ae01982-a4c0-4d70-9e02-f004ba68bed3)

**4. Удалить украшение:**  
![Снимок экрана 2025-06-14 130929](https://github.com/user-attachments/assets/68200933-9135-4925-ac73-eba931155f67)


## Пример запросов при работе с драгоценными металлами
**1. Создание драгоценных металлов:**  
![Снимок экрана 2025-06-14 131338](https://github.com/user-attachments/assets/d3419863-366e-4ba1-a57d-6c10d55f9b36)
![Снимок экрана 2025-06-14 131344](https://github.com/user-attachments/assets/c82caa4e-f141-4ec8-b717-7cd72bc03814)

**2. Редактировать драгоценные металлы:**  
![Снимок экрана 2025-06-14 131453](https://github.com/user-attachments/assets/c97bb4c3-3c7b-4dd8-8344-d0f360b86616)
![Снимок экрана 2025-06-14 131459](https://github.com/user-attachments/assets/651c1b57-ac12-47f1-846a-ad302ebf235f)

**3. Все драгоценные металлы:**  
![Снимок экрана 2025-06-14 131221](https://github.com/user-attachments/assets/b4e0dae1-24c0-4718-b328-fb3f6e3c1c46)

**4. Удалить драгоценные металлы:**  
![Снимок экрана 2025-06-14 131538](https://github.com/user-attachments/assets/45c565e0-e2b1-4f8d-b23c-4ccb2f5a9797)


## Пример запросов при работе с историей
**1. Получение истории по UIN:**  
![Снимок экрана 2025-06-14 130259](https://github.com/user-attachments/assets/94282d96-1229-49f4-94af-387d62fdf221)
![Снимок экрана 2025-06-14 130314](https://github.com/user-attachments/assets/fe0880fa-3062-48ee-ba38-d63b6edb0603)

**2. Обновить историю по UIN:**  
![Снимок экрана 2025-06-14 130506](https://github.com/user-attachments/assets/6dd69287-6499-4de8-b8de-2f61435c24dd)

**3. Очистить кэш истории:**
![Снимок экрана 2025-06-14 130506](https://github.com/user-attachments/assets/d7b2bd7d-d810-45e5-84bd-8e4eb8d55aab)

### Требования
- Java 17+
- PostgreSQL 14+
- Maven 3.8+

### Запуск
```bash
git clone https://github.com/vbalakin313/jewelry-manager-api
cd jewelry-manager-api
```

### Docker
```bash

# Сборка и запуск контейнеров (приложение PostgreSQL + Spring Boot)
docker-compose up --build -d

# Проверка запущенных контейнеров
docker-compose ps

# Просмотр логов приложения
docker-compose logs -f jewelry_manager_api_app

# Корректное завершение работы (сохранение базы данных)
docker-compose down

# Полная очистка (удаление тома базы данных)
docker-compose down -v
