# Imagen de JDK 17
FROM openjdk:17-jdk-slim

# Directorio de trabajo y copia del pom
WORKDIR /app
COPY pom.xml ./
COPY src ./src

# Copiar los scripts de Maven
COPY mvnw ./
COPY .mvn .mvn

# Construicción y copia del JAR
RUN ./mvnw clean package -DskipTests
COPY target/software-0.0.1-SNAPSHOT.jar app_dux.jar

# Puerto
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app_dux.jar"]
