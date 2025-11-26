# --- Estágio 1: Build (Compilação) ---
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia apenas o pom.xml primeiro (para aproveitar cache de dependências do Docker)
COPY pom.xml .
# Baixa as dependências (se o pom não mudou, o Docker usa o cache aqui)
RUN mvn dependency:go-offline

# Copia o código fonte
COPY src ./src

# Compila e gera o .jar (pula testes pra agilizar o build)
RUN mvn clean package -DskipTests

# --- Estágio 2: Runtime (Execução Leve) ---
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia apenas o arquivo .jar gerado no estágio anterior
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para rodar
ENTRYPOINT ["java", "-jar", "app.jar"]
