# Product API

REST API para gerenciamento de produtos, desenvolvida com Spring Boot.

## Tecnologias

- **Java 17**
- **Spring Boot 4.0.3**
- **Spring Data JPA** — acesso a dados com Hibernate
- **PostgreSQL** — banco de dados relacional
- **Flyway** — versionamento e migração de schema
- **Bean Validation (Jakarta)** — validação de entrada
- **Lombok** — redução de boilerplate
- **Maven** — build e gerenciamento de dependências

## Estrutura do Projeto

```
src/main/java/com/example/product/
├── controller/        # Endpoints REST
├── service/           # Regras de negócio
├── repository/        # Acesso a dados (JPA)
├── domain/model/      # Entidade JPA
├── dto/               # Request/Response DTOs
├── mapper/            # Conversão Entity ↔ DTO
├── config/            # Configurações (CORS, JPA)
└── exception/         # Tratamento global de erros
```

## Endpoints

| Método | Rota                | Descrição               |
|--------|---------------------|-------------------------|
| GET    | `/api/products`     | Listar produtos (paginado) |
| GET    | `/api/products/{id}`| Buscar produto por ID   |
| POST   | `/api/products`     | Criar novo produto      |
| PUT    | `/api/products/{id}`| Atualizar produto       |
| DELETE | `/api/products/{id}`| Excluir produto         |

A API retorna respostas padronizadas no formato:

```json
{
  "status": 200,
  "data": { ... },
  "message": "Success",
  "path": "/api/products",
  "timestamp": "2026-02-26T10:30:00"
}
```

A listagem suporta paginação e ordenação via query params:

```
GET /api/products?page=0&size=10&sort=id,asc
```

## Pré-requisitos

- **Java 17+**
- **PostgreSQL** rodando na porta `5432`

## Configuração do Banco

Crie o banco de dados:

```sql
CREATE DATABASE productdb;
```

As credenciais padrão estão em `src/main/resources/application-dev.yml`:

| Propriedade | Valor                                         |
|-------------|-----------------------------------------------|
| URL         | `jdbc:postgresql://localhost:5432/productdb`  |
| Usuário     | `postgres`                                    |
| Senha       | `admin`                                       |

> As tabelas são criadas automaticamente pelo Flyway na primeira execução.

## Como Rodar

```bash
# Clonar o repositório
git clone <url-do-repositorio>
cd product

# Executar (o Maven Wrapper já está incluído)
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

## Frontend

Este backend foi desenvolvido para funcionar em conjunto com o frontend Angular disponível em [desafio-angular](https://github.com/seu-usuario/desafio-angular), que consome a API na porta `4200` (CORS já configurado).
