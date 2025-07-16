# Jewelry Manager API

## Project Description

Jewelry Manager API is a server-side solution for managing clients' jewelry and precious metals, implementing virtual safe deposit box functionality. The system provides a reliable toolkit for tracking and controlling valuables with unique identifier (UIN) generation and complete operation history.

- Centralized client data management
- Jewelry and precious metals accounting
- Unique identification number (UIN) generation  
- Full operation audit with change history
- RESTful API for integration with external systems

## Technology Stack

- **Language**: Java 17
- **Framework**: Spring Boot 3.x  
- **Database**: PostgreSQL  
- **Build System**: Maven  
- **API Documentation**: Swagger/OpenAPI  
- **Caching**: Spring Cache  
- **Testing**: JUnit 5, Mockito  

## System Entities

### Core Data Models:

1. **Client** - client information  
   - Personal data (first name, last name, gender)  
   - Country of origin  
   - Associated UIN  

2. **UIN** - unique identification number  
   - 1:1 relationship with client  
   - List of jewelry items  
   - List of precious metals  

3. **Jewelry** - jewelry item  
   - Name, description  
   - Weight, material  
   - Creation/update date  

4. **PreciousMetal** - precious metal  
   - Metal type, purity  
   - Form, weight  
   - Creation/update date  

## API Endpoints

### Clients
- `GET /api/v1/clients` - list all clients  
- `PUT /api/v1/clients` - create new client  
- `PATCH /api/v1/clients` - update client data  
- `DELETE /api/v1/clients/{id}` - delete client  

### UIN
- `GET /api/v1/client/uin?id={clientId}` - get client's UIN  
- `PUT /api/v1/client/{clientId}/uin` - create UIN for client  
- `GET /api/v1/client/{uin}/uin` - full UIN information  

### Jewelry
- `GET /api/v1/jewelries` - list all jewelry items  
- `PUT /api/v1/jewelries` - create new jewelry item  
- `PATCH /api/v1/jewelries` - update jewelry item  
- `DELETE /api/v1/jewelries/{id}` - delete jewelry item  

### Precious Metals
- `GET /api/v1/metals` - list all metals  
- `PUT /api/v1/metals` - create new metal record  
- `PATCH /api/v1/metals` - update record  
- `DELETE /api/v1/metals/{id}` - delete record  

### History
- `GET /api/v1/history?uin={uin}` - addition history  
- `POST /api/v1/history/update` - update history  
- `POST /api/v1/history/evict` - clear history cache  

## Project Launch

## API Documentation
After application startup:  
- Swagger UI: `http://localhost:8080/swagger-ui.html`  
- OpenAPI (JSON): `http://localhost:8080/v3/api-docs`  

**Swagger interface example:**
![Screenshot 2025-06-14 122135](https://github.com/user-attachments/assets/766b0f48-1413-45db-8b4f-2a5ddaf7511c)  
![Screenshot 2025-06-14 122230](https://github.com/user-attachments/assets/c55ff545-d1fc-4951-8948-925310f1866a)  
![Screenshot 2025-06-14 122156](https://github.com/user-attachments/assets/f78f1557-1cad-4188-b392-4060a20dec87)  
![Screenshot 2025-06-14 122147](https://github.com/user-attachments/assets/37a0ba4b-d5b3-4b41-811e-1982c7c95f24)  
![Screenshot 2025-06-14 122239](https://github.com/user-attachments/assets/4edaadf8-1a43-4a07-8291-8f55bb113244)  
![Screenshot 2025-06-14 122250](https://github.com/user-attachments/assets/2a60cbd2-05ea-4534-8823-3ea30b329f9c)  

## Request Examples
## Example of requests when working with clients
**1. Creating a client:**  
![Снимок экрана 2025-06-14 123432](https://github.com/user-attachments/assets/5d438fec-b4d6-41e2-ad58-8d0147ed6930)
![Снимок экрана 2025-06-14 123439](https://github.com/user-attachments/assets/168ec4e3-d17c-4aa3-84fc-bc4d88881f63)

**2. Edit a client:**  
![Снимок экрана 2025-06-14 125335](https://github.com/user-attachments/assets/3675cb64-da4f-418d-8b25-39e0667c8aeb)
![Снимок экрана 2025-06-14 125342](https://github.com/user-attachments/assets/ed81f3c5-0a5e-452e-b767-1d856710ac91)

**3. All clients:**  
![Снимок экрана 2025-06-14 124922](https://github.com/user-attachments/assets/17200906-16af-4639-a00e-f5d38c177f84)

**4. Delete a client:**  
![Снимок экрана 2025-06-14 124859](https://github.com/user-attachments/assets/c30180d3-7bf8-412f-a615-07f72f8be92a)

## Example of requests when working with UIN

**1. Create UIN by ID:**  
![Снимок экрана 2025-06-14 123701](https://github.com/user-attachments/assets/5e04720c-a334-41d6-8b8e-b84caf57bfb8)
![Снимок экрана 2025-06-14 123711](https://github.com/user-attachments/assets/87610eb2-f48c-4951-864d-7de9f6f6bdfb)

**2. Getting UIN by ID:**  
![Снимок экрана 2025-06-14 123819](https://github.com/user-attachments/assets/5d230454-9559-46da-88d4-7d5879dcedf4)
![Снимок экрана 2025-06-14 123837](https://github.com/user-attachments/assets/41f66d3d-03a9-476b-a179-96fef302bef4)

**3. Getting client full information by UIN:**  
![Снимок экрана 2025-06-14 125706](https://github.com/user-attachments/assets/c3e58c68-f7ef-49a7-8c80-b009f0d8e724)
![Снимок экрана 2025-06-14 125745](https://github.com/user-attachments/assets/9a8e53ec-e196-494c-a913-8ff681a4dacf)


## Example of requests when working with jewelry
**1. Creating a jewelry:**  
![Снимок экрана 2025-06-14 124143](https://github.com/user-attachments/assets/ddbca0cd-9705-440e-9f17-4430088c6e76)
![Снимок экрана 2025-06-14 124150](https://github.com/user-attachments/assets/5edfabad-8127-47fd-80c8-9cf058fc4e42)

**2. Edit a jewelry:**  
![Снимок экрана 2025-06-14 124525](https://github.com/user-attachments/assets/a1aa3b63-9131-4801-9669-5679f420172d)
![Снимок экрана 2025-06-14 124536](https://github.com/user-attachments/assets/f2ac952e-5820-480b-827b-c24300deb020)

**3. All jewelries:**  
![Снимок экрана 2025-06-14 130835](https://github.com/user-attachments/assets/7ae01982-a4c0-4d70-9e02-f004ba68bed3)

**4. Delete a jewelry:**  
![Снимок экрана 2025-06-14 130929](https://github.com/user-attachments/assets/68200933-9135-4925-ac73-eba931155f67)


## Example of requests when working with precious metals
**1. Creating a precious metals:**  
![Снимок экрана 2025-06-14 131338](https://github.com/user-attachments/assets/d3419863-366e-4ba1-a57d-6c10d55f9b36)
![Снимок экрана 2025-06-14 131344](https://github.com/user-attachments/assets/c82caa4e-f141-4ec8-b717-7cd72bc03814)

**2. Edit a precious metals:**  
![Снимок экрана 2025-06-14 131453](https://github.com/user-attachments/assets/c97bb4c3-3c7b-4dd8-8344-d0f360b86616)
![Снимок экрана 2025-06-14 131459](https://github.com/user-attachments/assets/651c1b57-ac12-47f1-846a-ad302ebf235f)

**3. All precious metals:**  
![Снимок экрана 2025-06-14 131221](https://github.com/user-attachments/assets/b4e0dae1-24c0-4718-b328-fb3f6e3c1c46)

**4. Delete a precious metals:**  
![Снимок экрана 2025-06-14 131538](https://github.com/user-attachments/assets/45c565e0-e2b1-4f8d-b23c-4ccb2f5a9797)


## Example of requests when working with history
**1. Getting history by UIN:**  
![Снимок экрана 2025-06-14 130259](https://github.com/user-attachments/assets/94282d96-1229-49f4-94af-387d62fdf221)
![Снимок экрана 2025-06-14 130314](https://github.com/user-attachments/assets/fe0880fa-3062-48ee-ba38-d63b6edb0603)

**2. Update history by UIN:**  
![Снимок экрана 2025-06-14 130506](https://github.com/user-attachments/assets/6dd69287-6499-4de8-b8de-2f61435c24dd)

**3. Clear history cache:**  
![Снимок экрана 2025-06-14 130506](https://github.com/user-attachments/assets/d7b2bd7d-d810-45e5-84bd-8e4eb8d55aab)

### Requirements
- Java 17+  
- PostgreSQL 14+  
- Maven 3.8+  

### Installation
1. Clone the repository:
```bash
git clone https://github.com/vbalakin313/jewelry-manager-api
cd jewelry-manager-api

# Build and start containers (PostgreSQL + Spring Boot app)
docker-compose up --build -d

# Check running containers
docker-compose ps

# View app logs
docker-compose logs -f jewelry_manager_api_app

# Graceful shutdown (preserves database)
docker-compose down

# Full cleanup (removes database volume)
docker-compose down -v