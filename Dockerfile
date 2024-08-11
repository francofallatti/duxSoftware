# Dockerfile Multistage

# Fase de construcción
# Imagen de JDK 17
FROM openjdk:17-jdk-slim AS build

# Directorio de trabajo
WORKDIR /software

# Copiar archivos de construcción
COPY pom.xml ./
COPY src ./src
COPY mvnw ./
COPY .mvn .mvn

# Construir la aplicación
RUN ./mvnw clean package -DskipTests

# Fase final
# Imagen de JDK 17
FROM openjdk:17-jdk-slim

# Directorio de trabajo
WORKDIR /software

# Copia el archivo JAR desde la fase de construcción
COPY --from=build /software/target/software-0.0.1-SNAPSHOT.jar app_dux.jar

# Puerto
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app_dux.jar"]