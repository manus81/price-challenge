# Usar la imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Establecer el directorio de trabajo en /app
WORKDIR /app

# Copiar el archivo pom.xml en el contenedor
COPY pom.xml .

# Descargar las dependencias de Maven sin ejecutar la compilación
RUN mvn dependency:go-offline

# Copiar el codigo fuente
COPY src /app/src

# Construir el JAR
RUN mvn clean package -DskipTests

# Exponer el puerto donde se ejecuta la app (en este caso el puerto 8080)
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "/app/target/price-challenge-0.0.1-SNAPSHOT.jar"]
