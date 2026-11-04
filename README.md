# API REST de Series - Spring Boot

API REST para la gestion de un catalogo de series de television, implementando las operaciones CRUD (Create, Read, Update, Delete) utilizando Spring Boot, Spring Data JPA y base de datos H2.

---

## Especificaciones del Proyecto

### Entidad Serie
La entidad Serie contiene los siguientes campos:

| Campo | Tipo | Descripcion |
| :--- | :--- | :--- |
| `id` | `Long` | Identificador unico autogenerado (`@Id`, `@GeneratedValue`) |
| `titulo` | `String` | Nombre de la serie |
| `plataforma` | `String` | Plataforma de streaming (Netflix, HBO, Prime, Disney+, etc.) |
| `calificacion` | `Double` | Calificacion del 1 al 10 |

---

## Estructura del Proyecto

El proyecto sigue una arquitectura en capas:

```text
series/
├── pom.xml
├── mvnw / mvnw.cmd
├── ENDPOINTS.pdf                             # Documento con las capturas de las pruebas
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/munoz/series/
│   │   │   ├── SeriesApplication.java        # Clase principal
│   │   │   ├── controller/
│   │   │   │   └── SerieController.java      # Controlador REST (/api/series)
│   │   │   ├── service/
│   │   │   │   └── SerieService.java         # Logica de negocio
│   │   │   ├── repository/
│   │   │   │   └── SerieRepository.java      # Repositorio JPA
│   │   │   └── models/
│   │   │       └── Serie.java                # Entidad JPA con Lombok
│   │   └── resources/
│   │       └── application.properties        # Configuracion de H2 y servidor
│   └── test/
│       └── java/com/munoz/series/
│           └── SeriesApplicationTests.java   # Prueba de carga de contexto
```

---

## Ejecucion en IntelliJ IDEA

### Requisitos previos
- IntelliJ IDEA (Community o Ultimate)
- JDK 27 (o JDK compatible configurado en Project Structure)

### Pasos para abrir y ejecutar el proyecto

1. **Abrir el proyecto:**
   - Abre IntelliJ IDEA.
   - Selecciona **File** > **Open...** (o **Open** en la pantalla de bienvenida).
   - Navega hasta la carpeta `series` y seleccionala.
   - Elige **Open as Project**.

2. **Sincronizacion de Maven:**
   - IntelliJ detectara automaticamente el archivo `pom.xml`.
   - Espera a que termine de descargar las dependencias y de indexar el proyecto (veras el progreso en la barra inferior).

3. **Ejecutar la aplicacion:**
   - En el explorador de archivos del proyecto, navega a:
     `src/main/java/com/munoz/series/SeriesApplication.java`
   - Haz clic derecho sobre el archivo o haz clic en el icono verde de ejecucion (Run) al lado del metodo `main`.
   - Selecciona **Run 'SeriesApplication'** (o presiona `Shift + F10`).
   - La aplicacion iniciara en el puerto **8080**:
     ```text
     http://localhost:8080
     ```

---

## Consola de Base de Datos H2

La base de datos H2 se almacena en el archivo local `./data/seriesdb` con persistencia:

- **URL de la consola**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **Driver Class**: `org.h2.Driver`
- **JDBC URL**: `jdbc:h2:file:./data/seriesdb;AUTO_SERVER=TRUE`
- **User Name**: `sa`
- **Password**: *(dejar en blanco)*

---

## Endpoints de la API

| Metodo HTTP | URL | Descripcion | Codigo de Respuesta |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/series` | Listar todas las series | `200 OK` |
| **GET** | `/api/series/{id}` | Buscar una serie por su ID | `200 OK` / `404 Not Found` |
| **POST** | `/api/series` | Crear una nueva serie | `201 Created` |
| **PUT** | `/api/series/{id}` | Actualizar una serie existente | `200 OK` / `404 Not Found` |
| **DELETE** | `/api/series/{id}` | Eliminar una serie | `204 No Content` / `404 Not Found` |

---

## Ejemplos de Peticiones

### 1. Crear una nueva serie (POST)
**URL:** `POST http://localhost:8080/api/series`

**Body (JSON):**
```json
{
  "titulo": "Stranger Things",
  "plataforma": "Netflix",
  "calificacion": 8.7
}
```

**Respuesta (201 Created):**
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
**URL:** `GET http://localhost:8080/api/series`

**Respuesta (200 OK):**
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
**URL:** `GET http://localhost:8080/api/series/1`

**Respuesta (200 OK):**
```json
{
  "id": 1,
  "titulo": "Stranger Things",
  "plataforma": "Netflix",
  "calificacion": 8.7
}
```

**Caso de ID inexistente:**
- **URL:** `GET http://localhost:8080/api/series/999`
- **Respuesta:** `404 Not Found`

---

### 4. Actualizar serie existente (PUT)
**URL:** `PUT http://localhost:8080/api/series/1`

**Body (JSON):**
```json
{
  "titulo": "Stranger Things Temporada 4",
  "plataforma": "Netflix",
  "calificacion": 9.2
}
```

**Respuesta (200 OK):**
```json
{
  "id": 1,
  "titulo": "Stranger Things Temporada 4",
  "plataforma": "Netflix",
  "calificacion": 9.2
}
```

**Caso de ID inexistente:**
- **URL:** `PUT http://localhost:8080/api/series/999`
- **Respuesta:** `404 Not Found`

---

### 5. Eliminar serie (DELETE)
**URL:** `DELETE http://localhost:8080/api/series/1`

**Respuesta:** `204 No Content`

**Caso de ID inexistente:**
- **URL:** `DELETE http://localhost:8080/api/series/999`
- **Respuesta:** `404 Not Found`

---

## Evidencias de Pruebas

Las fotos y capturas de pantalla solicitadas que demuestran las pruebas y el correcto funcionamiento de cada uno de los endpoints CRUD se encuentran en el archivo **`ENDPOINTS.pdf`** ubicado en la raiz de este proyecto.
