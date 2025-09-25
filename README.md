# 馃摝 Proyecto Materiales - Full Stack

Este proyecto es una soluci贸n **Full Stack** que implementa la gesti贸n de materiales, con un **backend en Java Spring Boot** y un **frontend en Angular 19**.  
Incluye autenticaci贸n JWT, documentaci贸n Swagger, validaciones, y conexi贸n a base de datos SQL Server.

---

## 馃殌 Tecnolog铆as Utilizadas

### Backend
- Java 17 + Spring Boot 3
- Spring Data JPA
- Spring Security con JWT
- Lombok
- Swagger (springdoc-openapi)
- SQL Server 2019+

### Frontend
- Angular 19 (Standalone Components)
- Bootstrap 5
- TypeScript
- HTML5 + CSS3

### Herramientas
- IntelliJ IDEA (backend)
- Visual Studio Code (frontend)
- Git + GitHub
- Postman (colecci贸n incluida para probar API)

---

## 鈿欙笍 Backend (Spring Boot)

### 鈻讹笍 Ejecuci贸n
```bash
cd backendfinal
mvn spring-boot:run
```
Se levanta en: **http://localhost:9000**

### 馃搶 Configuraci贸n Base de Datos (SQL Server)
Archivo: `src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=materiales_db;encrypt=false;trustServerCertificate=true;
spring.datasource.username=sa
spring.datasource.password=12345

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
```

### 馃梽锔?Tablas Principales
- Departamentos
- Ciudades
- Materiales

Incluye validaciones:
- La fecha de compra nunca puede ser mayor a la de venta.
- Estados permitidos: `ACTIVO`, `DISPONIBLE`, `ASIGNADO`.

### 馃攽 Seguridad (JWT)
- Endpoint de login: `POST /auth/login`
- Genera un JWT v谩lido por **1 hora**.
- Debe enviarse en el header de las peticiones:
  ```http
  Authorization: Bearer <token>
  ```

### 馃摉 Swagger
Disponible en:  
馃憠 **http://localhost:9000/swagger-ui.html**

### 馃搶 Endpoints API

#### Materiales
- `GET /api/materiales` 鈫?Lista todos los materiales.
- `GET /api/materiales/{id}` 鈫?Busca material por ID.
- `POST /api/materiales` 鈫?Crear nuevo material.
- `PUT /api/materiales/{id}` 鈫?Actualizar material existente.
- `DELETE /api/materiales/{id}` 鈫?Eliminar material por ID.
- `GET /api/materiales/tipo/{tipo}` 鈫?Buscar por tipo.
- `GET /api/materiales/fecha-compra?inicio=YYYY-MM-DD&fin=YYYY-MM-DD` 鈫?Buscar por rango de fechas de compra.
- `GET /api/materiales/ciudad/{ciudadId}` 鈫?Buscar por ciudad.
- `GET /api/materiales/estado/{estado}` 鈫?Buscar por estado.

#### Ciudades y Departamentos
- `GET /api/ciudades`  
- `GET /api/departamentos`

---

## 馃帹 Frontend (Angular 19)

### 鈻讹笍 Ejecuci贸n
```bash
cd frontend
npm install
npm start
```
Se levanta en: **http://localhost:4200**

### Funcionalidades
- **Login con JWT** (usuario/contrase帽a 鈫?token almacenado en `localStorage`).
- Gesti贸n de materiales:
  - Listar todos
  - Buscar por tipo
  - Buscar por fecha de compra
  - Buscar por ciudad
  - Buscar por estado
  - Crear nuevos
  - Editar existentes
- Validaciones en frontend:
  - Fechas coherentes (compra 鈮?venta)
  - Campos obligatorios

### UI/UX
- Bootstrap 5 + CSS personalizado
- Mensajes de 茅xito/error con alertas
- Tabla din谩mica para materiales

---

## 馃攼 Credenciales de prueba
```
usuario: sa
password: 12345
```

---

## 馃搨 Estructura del Repositorio

```
materiales-proyecto/
鈹傗攢鈹€ backendfinal/        # C贸digo backend Java Spring Boot
鈹傗攢鈹€ frontend/            # C贸digo frontend Angular 19
鈹傗攢鈹€ README.md            # Documentaci贸n del proyecto
鈹傗攢鈹€ BaseDatos.sql        # Script inicial de BD (opcional)
鈹傗攢鈹€ MaterialesAPI.postman_collection.json # Colecci贸n Postman
```

---

## 馃搶 Puertos
- **Backend:** 9000
- **Frontend:** 4200
- **Base de Datos (SQL Server):** 1433

---

## 鉁?Puntos Adicionales
- Swagger para documentaci贸n
- JWT para autenticaci贸n
- Manejo de excepciones
- Validaci贸n de fechas
- Logs en backend

---

## 馃 Autor
**Jhon Jairo L贸pez S谩ez**  
馃殌 Proyecto t茅cnico Full Stack - Gesti贸n de Materiales
