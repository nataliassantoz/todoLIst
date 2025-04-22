# Etapa 1: Escolhe a imagem base com Java (versão 17)
FROM openjdk:17-jdk-slim

# Etapa 2: Cria uma pasta chamada /app dentro do container
WORKDIR /app

# Etapa 3: Copia o .jar da sua aplicação para dentro da pasta /app no container
COPY target/todolist-0.0.1-SNAPSHOT.jar app.jar

# Etapa 4: Expõe a porta 8080 (a mesma do Spring Boot)
EXPOSE 8080

# Etapa 5: Diz ao Docker como iniciar o app
ENTRYPOINT ["java", "-jar", "app.jar"]
