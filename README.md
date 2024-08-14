# Guía de Ejecución de la Prueba Técnica para Dux Software

## Pasos para Ejecutar la Aplicación

1. Abre Postman e importa el environment `dux.postman_environment.json` y la colección `DuxSoftware.postman_collection.json`.
2. Asegúrate de que Docker Desktop esté corriendo.
3. Abre una consola y ejecuta los siguientes comandos:
    ```shell
        docker build -t dux-app .
        docker run -p 8080:8080 dux-app
    ```
4. Docker se encargará de construir y ejecutar la aplicación. Desde Postman, podrás probar la API.
5. Para una vista más detallada de los endpoints, visita [Swagger UI](http://localhost:8080/swagger-ui/index.html#/).