# Usamos una imagen base de Java 11
FROM openjdk:11-jre-slim

# Definimos el directorio de trabajo en el contenedor
WORKDIR /app

# Copiamos el archivo JAR de nuestra aplicación al contenedor
COPY target/*.jar app.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]