# DUX SOFTWARE - PRUEBA TÉCNICA

## Guía de Ejecución de la Prueba Técnica para Dux Software

### Pasos para Ejecutar la Aplicación

1. Abre Postman e importa el environment `dux.postman_environment.json` y la colección `DuxSoftware.postman_collection.json`.
2. Asegúrate de que Docker Desktop esté corriendo.
3. Abre una consola y ejecuta los siguientes comandos:
   ```shell
       docker build -t dux-app .
       docker run -p 8080:8080 dux-app
   ```
4. Docker se encargará de construir y ejecutar la aplicación. Desde Postman, podrás probar la API.
5. Para una vista más detallada de los endpoints, visita [Swagger UI](http://localhost:8080/swagger-ui/index.html#/).

---

## Desarrollador Java Spring - Consigna

Desarrollar, utilizando Spring Boot 3 y Java 17, una API para gestionar información de equipos de fútbol. La aplicación debe proporcionar las siguientes funcionalidades:

### 1. Consulta de Todos los Equipos

- **Endpoint:** `GET /equipos`
- **Respuesta Exitosa (200 OK):** Devuelve la lista de todos los equipos de fútbol registrados.

  ```json
  [
    {
      "id": 2,
      "nombre": "FC Barcelona",
      "liga": "La Liga",
      "pais": "España"
    },
    {
      "id": 3,
      "nombre": "Manchester United",
      "liga": "Premier League",
      "pais": "Inglaterra"
    }
    // Otros equipos...
  ]
  ```

### 2. Consulta de un Equipo por ID:

- **Endpoint:** `GET /equipos/{id}`
- **Respuesta Exitosa (200 OK):** Devuelve la información del equipo correspondiente al
  ID proporcionado.

  ```json
  {
    "id": 2,
    "nombre": "Barcelona FC",
    "liga": "La Liga",
    "pais": "España"
  }
  ```

- **Respuesta Error (404 Not Found):** Si el equipo no existe.

  ```json
  {
    "mensaje": "Equipo no encontrado",
    "codigo": 404
  }
  ```

### 3. Búsqueda de Equipos por Nombre:

- **Endpoint:** `GET /equipos/buscar?nombre={valor}`
- **Respuesta Exitosa (200 OK):** Devuelve la lista de equipos cuyos nombres contienen
  el valor proporcionado en el parámetro de búsqueda.

  ```json
  [
    {
      "id": 1,
      "nombre": "Real Madrid",
      "liga": "La Liga",
      "pais": "España"
    },
    {
      "id": 2,
      "nombre": "FC Barcelona",
      "liga": "La Liga",
      "pais": "España"
    }
    // Otros equipos...
  ]
  ```

### 4. Creación de un equipo:

- **Endpoint:** `POST /equipos/`
- **Cuerpo de la Petición:** Datos del nuevo equipo

  ```json
  {
    "nombre": "Nuevo Equipo FC",
    "liga": "Nueva Liga",
    "pais": "Nuevo País"
  }
  ```

- **Respuesta exitosa (201 Created):**

  ```json
  {
    "id": 26,
    "nombre": "Nuevo Equipo FC",
    "liga": "Nueva Liga",
    "pais": "Nuevo País"
  }
  ```

- **Respuesta error (400 Bad Request):**

  ```json
  {
    "mensaje": "La solicitud es invalida",
    "codigo": 400
  }
  ```

### 5. Creación de un equipo:

- **Endpoint:** `PUT /equipos/{id}`
- **Cuerpo de la Petición:** Datos actualizados del equipo.

  ```json
  {
    "nombre": "Nuevo Nombre",
    "liga": "Nueva Liga",
    "pais": "Nuevo País"
  }
  ```

- **Respuesta exitosa (200 Ok):** Devuelve la información actualizada del equipo.

  ```json
  {
    "id": 1,
    "nombre": "Nuevo Nombre",
    "liga": "Nueva Liga",
    "pais": "Nuevo País"
  }
  ```

- **Respuesta error (404 Not Found):** Si el equipo no existe.

  ```json
  {
    "mensaje": "Equipo no encontrado",
    "codigo": 404
  }
  ```

### 6. Eliminación de un Equipo:

- **Endpoint:** `DELETE /equipos/{id}`
- **Respuesta Exitosa (204 No Content):** Sin contenido.
- **Respuesta error (404 Not Found):** Si el equipo no existe.

  ```json
  {
    "mensaje": "Equipo no encontrado",
    "codigo": 404
  }
  ```

### Aspectos a tener en cuenta:

- Escribir pruebas unitarias para al menos un servicio de la aplicación. Utilizar
  mocks para la base de datos.
- La persistencia se debe hacer con base de datos h2 en memoria.
- Dockerización: proporcionar un Dockerfile para construir la imagen de la
  aplicación.
- Seguridad: utilizar JWT para la autenticación. Crear un usuario por defecto con
  nombre de usuario “test” y password “12345”. Para la autenticación, se debe
  poder enviar una petición a `/auth/login` con body:

  ```json
  {
    "username": "test",
    "password": "12345"
  }
  ```

  En caso de que la autenticación no sea exitosa, deberá devolver una respuesta
  con código 401. Para autenticación exitosa debe ser con código http 200 y un
  json que contenga el token:

  ```json
  {
    "token": <TOKEN>
  }
  ```

- Manejo de Excepciones: Implementar un manejo adecuado de excepciones para
  casos como errores de validación, problemas de base de datos u otras
  situaciones inesperadas.
- Generar documentación para la API utilizando herramientas como Swagger
  para facilitar su comprensión y uso por parte de otros desarrolladores.
- Respetar los formatos de las respuestas, endpoints y datos planteados en el
  enunciado

### Datos a Utilizar:

Se debe crear un script SQL que inicialice la base de datos con los siguientes equipos:

| ID  | Nombre                    | Liga                 | País         |
| --- | ------------------------- | -------------------- | ------------ |
| 1   | Real Madrid               | La Liga              | España       |
| 2   | FC Barcelona              | La Liga              | España       |
| 3   | Manchester United         | Premier League       | Inglaterra   |
| 4   | Liverpool FC              | Premier League       | Inglaterra   |
| 5   | Juventus FC               | Serie A              | Italia       |
| 6   | AC Milan                  | Serie A              | Italia       |
| 7   | Bayern Munich             | Bundesliga           | Alemania     |
| 8   | Borussia Dortmund         | Bundesliga           | Alemania     |
| 9   | Paris Saint-Germain       | Ligue 1              | Francia      |
| 10  | Olympique de Marseille    | Ligue 1              | Francia      |
| 11  | FC Porto                  | Primeira Liga        | Portugal     |
| 12  | Sporting CP               | Primeira Liga        | Portugal     |
| 13  | Ajax Amsterdam            | Eredivisie           | Países Bajos |
| 14  | Feyenoord                 | Eredivisie           | Países Bajos |
| 15  | Celtic FC                 | Scottish Premiership | Escocia      |
| 16  | Rangers FC                | Scottish Premiership | Escocia      |
| 17  | Galatasaray SK            | Süper Lig            | Turquía      |
| 18  | Fenerbahçe SK             | Süper Lig            | Turquía      |
| 19  | FC Zenit Saint Petersburg | Premier League Rusa  | Rusia        |
| 20  | Spartak Moscow            | Premier League Rusa  | Rusia        |
| 21  | SL Benfica                | Primeira Liga        | Portugal     |
| 22  | Besiktas JK               | Süper Lig            | Turquía      |
| 23  | SSC Napoli                | Serie A              | Italia       |
| 24  | Atlético Madrid           | La Liga              | España       |
