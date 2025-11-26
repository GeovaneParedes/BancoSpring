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
