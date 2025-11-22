# API REST para Gestión de Productos

Este proyecto es una API REST completa desarrollada con Spring Boot para la gestión de productos en un sistema de e-commerce.

## 📝 Descripción
La API permite realizar operaciones CRUD completas sobre productos, incluyendo filtrado por categoría y control de stock. Implementa validaciones, manejo de excepciones y documentación automática con Swagger/OpenAPI.

## ⚙️ Tecnologías Utilizadas
- **Java 21**
- **Spring Boot 3.x** (Web, Data JPA, Validation, DevTools)
- **H2 Database** (Base de datos en memoria)
- **Lombok**
- **Gradle**
- **Swagger / OpenAPI**

## 🚀 Instrucciones de Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone <url-del-repositorio>
   ```
2. **Navegar al directorio del proyecto:**
   ```bash
   cd productos-api
   ```
3. **Ejecutar la aplicación:**
   ```bash
   ./gradlew bootRun
   ```
   (En Windows: `gradlew bootRun`)

## 🌐 Documentación API (Swagger)
Una vez iniciada la aplicación, accede a la documentación interactiva en:
http://localhost:8080/swagger-ui/index.html

## 🗃️ Consola H2
Para inspeccionar la base de datos en memoria:
- **URL:** http://localhost:8080/h2-console
- **JDBC URL:** `jdbc:h2:mem:productos_db`
- **User:** `sa`
- **Password:** (vacío)

## 📡 Endpoints Principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/productos` | Listar todos los productos |
| GET | `/api/productos/{id}` | Obtener producto por ID |
| GET | `/api/productos/categoria/{categoria}` | Filtrar por categoría |
| POST | `/api/productos` | Crear nuevo producto |
| PUT | `/api/productos/{id}` | Actualizar producto completo |
| PATCH | `/api/productos/{id}/stock` | Actualizar stock |
| DELETE | `/api/productos/{id}` | Eliminar producto |

## 📸 Capturas de Pantalla

### 1. Documentación Completa (Swagger UI)
![Swagger UI Completo](./capturas/swagger-completo.png)

### 2. Creación de Producto (POST 201)
![POST Producto](./capturas/post-producto.png)

### 3. Listado de Productos (GET 200)
![GET Productos](./capturas/get-productos.png)

### 4. Error: Producto No Encontrado (404)
![Error 404](./capturas/error-404.png)

### 5. Error: Validación Fallida (400)
![Error 400](./capturas/error-400.png)

### 6. Persistencia en H2 Console
![H2 Console](./capturas/h2-console.png)

## 💭 Conclusiones
En este trabajo práctico integrador he aprendido a:
- **Arquitectura en Capas**: Comprendí la importancia de separar responsabilidades (Controller, Service, Repository, Model) para mantener un código limpio y mantenible.
- **DTOs**: Aprendí a utilizar Data Transfer Objects para desacoplar la entidad de base de datos de la información que se expone en la API, permitiendo mayor seguridad y flexibilidad.
- **Validaciones**: Implementé `Bean Validation` para asegurar la integridad de los datos antes de que lleguen a la lógica de negocio.
- **Manejo de Errores**: Utilicé `@ControllerAdvice` para centralizar el manejo de excepciones y ofrecer respuestas HTTP consistentes y claras al cliente.
- **Documentación**: Descubrí cómo Swagger/OpenAPI facilita la documentación y prueba de los endpoints sin necesidad de herramientas externas como Postman.

Este proyecto me permitió consolidar los conocimientos de Spring Boot y entender el flujo completo de una petición HTTP desde que llega al controlador hasta que se persiste en la base de datos.

## 👤 Autor
Juan Cruz Pereyra Muñoz
Legajo: 52732