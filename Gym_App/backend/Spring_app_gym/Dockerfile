# Usar una imagen base de Java 17 con Maven
FROM maven:3.9.6-eclipse-temurin-17 as build

# Crear directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml
COPY pom.xml .

# Copiar el código fuente
COPY src ./src

# Construir la aplicación
RUN mvn clean package -DskipTests

# Usar una imagen más ligera para la ejecución
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar el JAR desde la etapa de construcción
COPY --from=build /app/target/App_gym-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 3000
EXPOSE 3000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"] 