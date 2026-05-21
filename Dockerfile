# ETAPA 1: Construcción (Compilar el proyecto usando Maven)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Crear la imagen final usando Eclipse Temurin Java 17)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copiamos el .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar
# Exponemos el puerto 8080
EXPOSE 8080
# Comando para iniciar el Web Service
ENTRYPOINT ["java", "-jar", "app.jar"]
