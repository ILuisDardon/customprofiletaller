# Etapa 1: Compilar la aplicación
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos del proyecto Maven
COPY pom.xml .
COPY src ./src

# Ejecuta la compilación de Maven para crear el archivo JAR de la aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Crear una imagen minimalista para ejecutar la aplicación
FROM eclipse-temurin:21-jre

# Crea un directorio para la aplicación
WORKDIR /app

# Copia el archivo JAR desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Configura la variable de entorno para limitar el uso de memoria en la JVM
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]