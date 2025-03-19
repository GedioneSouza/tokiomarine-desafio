# Prova Teste - API REST com JPA

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/java-11%2B-orange)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.4-green)](https://spring.io/projects/spring-boot)
[![H2 Database](https://img.shields.io/badge/H2-Database-blue)](https://www.h2database.com/)

## Descrição do Projeto

Este projeto consiste em uma API REST desenvolvida utilizando **Spring Boot** e **JPA** para gerenciar clientes e seus endereços. A API permite operações de **CRUD** (Create, Read, Update, Delete) para clientes e endereços, além de integrar um serviço externo para buscar informações de endereço a partir de um CEP.

A conexão com o banco de dados foi configurada utilizando o **H2 Database**, um banco de dados em memória que facilita o desenvolvimento e testes. O mapeamento das entidades e a carga inicial de dados podem ser verificados no arquivo `data.sql`.

## Funcionalidades Principais

1. **CRUD de Clientes**:
   - Criar, listar, buscar por ID, atualizar e excluir clientes.
   - Cada cliente pode ter um ou mais endereços associados.

2. **CRUD de Endereços**:
   - Criar, listar, buscar por ID, atualizar e excluir endereços.
   - Os endereços estão vinculados a um cliente específico.

3. **Integração com API de CEP**:
   - Consumo da API [BrasilAberto](https://api.brasilaberto.com/v1/zipcode/{cep}) para buscar informações de endereço a partir de um CEP.
   - Exemplo de uso: `GET /addresses/cep/{cep}`.

4. **Documentação Automatizada**:
   - A API utiliza **Swagger** para documentação interativa, facilitando o teste e a integração.

## Tecnologias Utilizadas

- **Linguagem de Programação**: Java 11+
- **Framework**: Spring Boot 2.1.5.RELEASE
- **Banco de Dados**: H2 Database (em memória)
- **Bibliotecas**:
  - Spring Data JPA (para persistência de dados)
  - Swagger (para documentação da API)
- **Ferramentas de Desenvolvimento**:
  - Maven (gerenciamento de dependências)
  - Postman (testes de API)

## Como Executar o Projeto

### Pré-requisitos

- [Java 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Postman](https://www.postman.com/downloads/) (opcional, para testes manuais)

### Instalação e Execução

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/prova-teste.git
   cd prova-teste
   ```

2. Compile o projeto com Maven:

   ```bash
   mvn clean install
   ```

3. Execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

   A API estará disponível em `http://localhost:8080`.

4. Acesse a documentação interativa:

   - **Swagger UI**: `http://localhost:8080/swagger-ui.html`
   - **H2 Database Console**: `http://localhost:8080/h2-console` (use `jdbc:h2:mem:testdb` como URL de conexão).

### Exemplos de Requisições

#### Clientes

- **Listar todos os clientes**:
  ```bash
  GET /clients
  ```

- **Buscar cliente por ID**:
  ```bash
  GET /clients/{id}
  ```

- **Criar um novo cliente**:
  ```bash
  POST /clients
  Body:
  {
    "firstName": "João",
    "lastName": "Silva",
    "email": "joao.silva@example.com",
    "addresses": [
      {
        "address": "Rua Exemplo",
        "number": "123",
        "complement": "Apto 101",
        "postalCode": "01001000",
        "city": "São Paulo",
        "state": "SP",
        "country": "Brasil"
      }
    ]
  }
  ```

- **Atualizar um cliente**:
  ```bash
  PUT /clients/{id}
  Body:
  {
    "firstName": "João",
    "lastName": "Silva",
    "email": "joao.silva@example.com",
    "addresses": [
      {
        "id": 1,
        "address": "Rua Exemplo",
        "number": "123",
        "complement": "Apto 101",
        "postalCode": "01001000",
        "city": "São Paulo",
        "state": "SP",
        "country": "Brasil"
      }
    ]
  }
  ```

- **Excluir um cliente**:
  ```bash
  DELETE /clients/{id}
  ```

#### Endereços

- **Listar todos os endereços**:
  ```bash
  GET /addresses
  ```

- **Buscar endereço por ID**:
  ```bash
  GET /addresses/{id}
  ```

- **Criar um novo endereço**:
  ```bash
  POST /addresses
  Body:
  {
    "address": "Rua Exemplo",
    "number": "123",
    "complement": "Apto 101",
    "postalCode": "01001000",
    "city": "São Paulo",
    "state": "SP",
    "country": "Brasil",
    "clientId": 1
  }
  ```

- **Atualizar um endereço**:
  ```bash
  PUT /addresses/{id}
  Body:
  {
    "address": "Rua Exemplo",
    "number": "123",
    "complement": "Apto 101",
    "postalCode": "01001000",
    "city": "São Paulo",
    "state": "SP",
    "country": "Brasil",
    "clientId": 1
  }
  ```

- **Excluir um endereço**:
  ```bash
  DELETE /addresses/{id}
  ```

- **Buscar endereço por CEP**:
  ```bash
  GET /addresses/cep/{cep}
  ```

## Estrutura do Projeto

```
api-java/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/tokiomarine/seguradora/
│   │   │       ├── controllers/          # Controladores da API
│   │   │       ├── domain/               # Entidades JPA
│   │   │       ├── dtos/                 # Data Transfer Objects (DTOs)
│   │   │       ├── infra/exceptions/     # Exceções personalizadas
|   |   |       ├── repositories          # Camadas de persistência
│   │   │       ├── services/             # Lógica de negócio
│   │   │       └── utils/                # Utilitários (ex: MapperUtils)
│   │   └── resources/
│   │       ├── data.sql                  # Carga inicial de dados
│   │       └── application.properties    # Configurações do Spring Boot
│   └── test/                             
├── pom.xml                               # Configuração do Maven
└── README.md                             # Documentação do projeto
```


## Contato

- **Nome**: Gedione Souza
- **Email**: gedionesouza2000@gmail.com
- **GitHub**: https://github.com/GedioneSouza
