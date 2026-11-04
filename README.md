# 📺 API REST de Series - Spring Boot

API REST completa para la gestión de un catálogo de series de televisión, implementando las operaciones CRUD (Create, Read, Update, Delete) desarrollada con **Spring Boot**, **Spring Data JPA** y base de datos **H2**.

---

## 📋 Especificaciones del Proyecto

### Entidad `Serie`
Contiene los siguientes campos:

| Campo | Tipo | Descripción |
| :--- | :--- | :--- |
| `id` | `Long` | Identificador único autogenerado (`@Id`, `@GeneratedValue`) |
| `titulo` | `String` | Nombre de la serie |
| `plataforma` | `String` | Plataforma de streaming (Netflix, HBO, Prime, Disney+, etc.) |
| `calificacion` | `Double` | Calificación del 1 al 10 |

---

## 📁 Estructura del Proyecto

El proyecto sigue una arquitectura en capas limpia, fiel al proyecto visto en clase:

```text
series/
├── pom.xml
├── mvnw / mvnw.cmd
├── postman_collection.json
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/munoz/series/
│   │   │   ├── SeriesApplication.java
│   │   │   ├── controller/
│   │   │   │   └── SerieController.java      # Controlador REST (/api/series)
│   │   │   ├── service/
│   │   │   │   └── SerieService.java         # Lógica de negocio
│   │   │   ├── repository/
│   │   │   │   └── SerieRepository.java      # Repositorio JPA
│   │   │   └── models/
│   │   │       └── Serie.java                # Entidad JPA con Lombok
│   │   └── resources/
│   │       └── application.properties        # Configuración H2 y servidor
│   └── test/
│       └── java/com/munoz/series/
│           └── SeriesApplicationTests.java   # Pruebas unitarias/integración
```

---

## 🚀 Requisitos y Ejecución

### Prerrequisitos
- Java 27 (o JDK compatible)
- Maven (el proyecto ya incluye el wrapper `./mvnw`)

### Ejecutar el proyecto
Abre una terminal en la carpeta `series/` y ejecuta:

```bash
./mvnw spring-boot:run
```

O en Windows:
```cmd
mvnw.cmd spring-boot:run
```

La aplicación se iniciará en el puerto **8080**:
```text
http://localhost:8080
```

### Ejecutar las pruebas automatizadas
Para ejecutar las pruebas del proyecto que validan el ciclo CRUD y casos edge:

```bash
./mvnw test
```

---

## 🗄️ Consola de Base de Datos H2

La base de datos H2 se almacena en archivo local `./data/seriesdb` para mantener los datos guardados:

- **URL de la consola**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL**: `jdbc:h2:file:./data/seriesdb`
- **User Name**: `sa`
- **Password**: *(dejar en blanco)*

---

## 🔧 Endpoints de la API

| Método HTTP | URL | Descripción | Código de Respuesta |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/series` | Listar todas las series | `200 OK` |
| **GET** | `/api/series/{id}` | Buscar una serie por su ID | `200 OK` / `404 Not Found` |
| **POST** | `/api/series` | Crear una nueva serie | `201 Created` |
| **PUT** | `/api/series/{id}` | Actualizar una serie existente | `200 OK` / `404 Not Found` |
| **DELETE** | `/api/series/{id}` | Eliminar una serie | `204 No Content` / `404 Not Found` |

---

## 🧪 Ejemplos de Peticiones (cURL y JSON)

### 1. Crear una nueva serie (POST)
**Endpoint:** `POST http://localhost:8080/api/series`

**Body (JSON):**
```json
{
  "titulo": "Stranger Things",
  "plataforma": "Netflix",
  "calificacion": 8.7
}
```

**Comando cURL:**
```bash
curl -X POST http://localhost:8080/api/series \
  -H "Content-Type: application/json" \
  -d '{"titulo": "Stranger Things", "plataforma": "Netflix", "calificacion": 8.7}'
```

**Respuesta (`201 Created`):**
```json
{
  "id": 1,
  "titulo": "Stranger Things",
  "plataforma": "Netflix",
  "calificacion": 8.7
}
```

---

### 2. Listar todas las series (GET)
**Endpoint:** `GET http://localhost:8080/api/series`

**Comando cURL:**
```bash
curl -X GET http://localhost:8080/api/series
```

**Respuesta (`200 OK`):**
```json
[
  {
    "id": 1,
    "titulo": "Stranger Things",
    "plataforma": "Netflix",
    "calificacion": 8.7
  }
]
```

---

### 3. Buscar serie por ID (GET)
**Endpoint:** `GET http://localhost:8080/api/series/1`

**Comando cURL:**
```bash
curl -X GET http://localhost:8080/api/series/1
```

**Respuesta (`200 OK`):**
```json
{
  "id": 1,
  "titulo": "Stranger Things",
  "plataforma": "Netflix",
  "calificacion": 8.7
}
```

**Caso Edge (ID no existe):**
```bash
curl -X GET http://localhost:8080/api/series/999
```
*Respuesta:* `404 Not Found`

---

### 4. Actualizar serie existente (PUT)
**Endpoint:** `PUT http://localhost:8080/api/series/1`

**Body (JSON):**
```json
{
  "titulo": "Stranger Things Temporada 4",
  "plataforma": "Netflix",
  "calificacion": 9.2
}
```

**Comando cURL:**
```bash
curl -X PUT http://localhost:8080/api/series/1 \
  -H "Content-Type: application/json" \
  -d '{"titulo": "Stranger Things Temporada 4", "plataforma": "Netflix", "calificacion": 9.2}'
```

**Respuesta (`200 OK`):**
```json
{
  "id": 1,
  "titulo": "Stranger Things Temporada 4",
  "plataforma": "Netflix",
  "calificacion": 9.2
}
```

**Caso Edge (ID no existe):**
```bash
curl -X PUT http://localhost:8080/api/series/999 \
  -H "Content-Type: application/json" \
  -d '{"titulo": "No Existe", "plataforma": "Ninguna", "calificacion": 1.0}'
```
*Respuesta:* `404 Not Found`

---

### 5. Eliminar serie (DELETE)
**Endpoint:** `DELETE http://localhost:8080/api/series/1`

**Comando cURL:**
```bash
curl -X DELETE http://localhost:8080/api/series/1
```

**Respuesta:** `204 No Content`

**Caso Edge (ID no existe):**
```bash
curl -X DELETE http://localhost:8080/api/series/999
```
*Respuesta:* `404 Not Found`

---

## 📮 Pruebas con Postman

Se incluye en la raíz del proyecto el archivo `postman_collection.json`.

### Cómo importarlo en Postman:
1. Abre Postman.
2. Haz clic en el botón **Import** (arriba a la izquierda).
3. Selecciona o arrastra el archivo `series/postman_collection.json`.
4. Encontrarás la colección **"API REST de Series - Spring Boot"** con todas las peticiones listas para ejecutar (incluyendo casos de éxito y casos edge con 404).

---

## 📤 Subir a Repositorio GitHub

Para subir el proyecto a tu cuenta de GitHub:

```bash
cd series
git init
git add .
git commit -m "feat: implementacion inicial API REST de Series con Spring Boot y H2"
git branch -M main
git remote add origin https://github.com/TU_USUARIO/TU_REPOSITORIO.git
git push -u origin main
```
