# Instructivo de Entrega - API REST Productos

Este documento detalla los pasos para cumplir con los requisitos de entrega del Trabajo Práctico.

## 1. Tabla de Endpoints

A continuación se detalla la documentación de los endpoints disponibles en la API:

| Método | Ruta | Descripción |
| :--- | :--- | :--- |
| **GET** | `/api/productos` | Obtener todos los productos. Retorna una lista de todos los productos disponibles. |
| **GET** | `/api/productos/{id}` | Obtener producto por ID. Retorna un producto específico. Si no existe, devuelve 404. |
| **GET** | `/api/productos/categoria/{categoria}` | Obtener productos por categoría. Filtra los productos (ej: ELECTRONICA, HOGAR). |
| **POST** | `/api/productos` | Crear un nuevo producto. Requiere un cuerpo JSON válido. Retorna 201 Created. |
| **PUT** | `/api/productos/{id}` | Actualizar producto. Reemplaza todos los datos del producto. |
| **PATCH** | `/api/productos/{id}/stock` | Actualizar stock. Modifica solo la cantidad de stock de un producto. |
| **DELETE** | `/api/productos/{id}` | Eliminar producto. Elimina el registro de la base de datos. |

---

## 2. Instrucciones de Acceso

### Ejecutar la Aplicación
1.  Abre una terminal en la carpeta raíz del proyecto.
2.  Ejecuta el siguiente comando:
    ```bash
    ./gradlew bootRun
    ```
    *(En Windows, si usas CMD, puede ser `gradlew bootRun` sin el `./`)*.
3.  Espera a que aparezca el mensaje `Started ProductosApiApplication in ...`.

### Acceder a Swagger UI
*   **URL:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
*   Aquí podrás ver la documentación interactiva y probar los endpoints.

### Acceder a la Consola H2
*   **URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
*   **Datos de conexión:**
    *   **Driver Class:** `org.h2.Driver`
    *   **JDBC URL:** `jdbc:h2:mem:productos_db`
    *   **User Name:** `sa`
    *   **Password:** *(dejar vacío)*
*   Haz clic en **Connect**.

---

## 3. Guía Paso a Paso para las Capturas de Pantalla

Sigue estos pasos para obtener las capturas solicitadas:

### 📸 Captura 1: Documentación completa de endpoints
1.  Ve a Swagger UI.
2.  Despliega el controlador `producto-controller` (o `Productos`).
3.  Toma una captura donde se vean todos los endpoints listados.

### 📸 Captura 2: Prueba exitosa de POST (creando producto)
1.  En Swagger, abre el endpoint **POST /api/productos**.
2.  Haz clic en "Try it out".
3.  Pega el siguiente JSON en el cuerpo de la petición:
    ```json
    {
      "nombre": "Smartphone Test",
      "descripcion": "Teléfono de prueba para captura",
      "precio": 500.00,
      "stock": 20,
      "categoria": "ELECTRONICA"
    }
    ```
4.  Haz clic en **Execute**.
5.  Toma una captura del apartado **Server response** mostrando el código **201** y el cuerpo de la respuesta.

### 📸 Captura 3: Prueba de GET (listando productos)
1.  En Swagger, abre el endpoint **GET /api/productos**.
2.  Haz clic en "Try it out" y luego en **Execute**.
3.  Toma una captura del **Server response** mostrando el código **200** y la lista de productos (debería aparecer el que creaste).

### 📸 Captura 4: Error 404 cuando producto no existe
1.  En Swagger, abre el endpoint **GET /api/productos/{id}**.
2.  Haz clic en "Try it out".
3.  Ingresa un ID inexistente, por ejemplo: `9999`.
4.  Haz clic en **Execute**.
5.  Toma una captura del **Server response** mostrando el código **404**.

### 📸 Captura 5: Error 400 de validación
1.  En Swagger, abre el endpoint **POST /api/productos**.
2.  Haz clic en "Try it out".
3.  Ingresa un JSON inválido (ej. precio negativo o nombre vacío):
    ```json
    {
      "nombre": "",
      "descripcion": "Error test",
      "precio": -100,
      "stock": 10,
      "categoria": "ELECTRONICA"
    }
    ```
4.  Haz clic en **Execute**.
5.  Toma una captura del **Server response** mostrando el código **400** y los mensajes de error de validación.

### 📸 Captura 6: Consola H2 con datos persistidos
1.  Ve a la consola H2 y conéctate.
2.  En el cuadro de SQL, escribe: `SELECT * FROM PRODUCTO;`
3.  Haz clic en **Run**.
4.  Toma una captura de la tabla de resultados mostrando los datos guardados.
