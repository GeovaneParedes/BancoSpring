# BancoSpring Enterprise 🏦 🐳

Sistema bancário Fullstack desenvolvido com **Java 21 (Spring Boot)** e **Docker**, simulando um ambiente corporativo com controle de acesso e persistência de dados.

## 🚀 Tecnologias
- **Backend:** Java 21, Spring Boot 3.2, Spring Data JPA
- **Frontend:** Thymeleaf + Bootstrap 5 (Server-Side Rendering)
- **Banco de Dados:** MySQL 8.0
- **DevOps:** Docker, Docker Compose (Infraestrutura como Código)

## ⚙️ Funcionalidades
- [x] **CRUD de Contas:** Criação e listagem.
- [x] **Segurança:** Login com senha para acessar dados sensíveis.
- [x] **Role-Based View:** Ocultação de saldo na listagem pública (mostra apenas o Cargo/Departamento).
- [x] **Transações:** Depósito e Saque com atualização em tempo real.
- [x] **Docker:** Ambiente containerizado com *Healthchecks* e Volumes.

## 🛠️ Como Rodar (Via Docker)

Você não precisa ter Java ou MySQL instalados. Apenas o **Docker**.

1. Clone o repositório:
   ```bash
   git clone [https://github.com/GeovaneParedes/BancoSpring.git](https://github.com/GeovaneParedes/BancoSpring.git)
   cd BancoSpring

## ☸️ Como Rodar no Kubernetes (Kind)

Para orquestrar os microsserviços localmente simulando um ambiente de produção:

### Pré-requisitos
- [Kind](https://kind.sigs.k8s.io/)
- [Kubectl](https://kubernetes.io/docs/tasks/tools/)

### Passo a Passo

1. **Crie o Cluster:**
   ```bash
   kind create cluster --name banco-cluster
   ```
2. **Carregue a imagem Docker para o Cluster:**

   ```bash
   docker build -t bancospring-app:v1 .
   kind load docker-image bancospring-app:v1 --name banco-cluster
   ```
3. Faça o Deploy (Manifestos):

   ```bash
   kubectl apply -f k8s/
   ```
4. Acesse a Aplicação (Port-Forward):

   ```bash
   kubectl port-forward service/banco-app-service 8080:80
   ```
   Acesse: http://localhost:8080

