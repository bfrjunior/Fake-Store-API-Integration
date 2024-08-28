# 🛒 Fake Store API Integration
Este projeto é uma API desenvolvida em Java 17 com Spring Boot que integra e consome dados da Fake Store API. A API oferece endpoints para buscar, salvar, atualizar e deletar produtos, utilizando um banco de dados PostgreSQL para persistência. O projeto também utiliza o Swagger para a documentação da API.

## 🛠️  Tecnologias Utilizadas
* Java 17
* Spring Boot
* PostgreSQL
* Swagger/OpenAPI 3
* Gradle 
## 🚀 Funcionalidades

A API possui as seguintes funcionalidades:

* Buscar Produtos da Fake Store API: Busca produtos diretamente da Fake Store API e os salva no banco de dados local.
* Salvar Produtos: Permite salvar novos produtos no banco de dados.
* Atualizar Produtos: Permite a atualização de produtos existentes no banco de dados.
* Deletar Produtos: Permite deletar produtos do banco de dados.
* Buscar Todos os Produtos: Retorna uma lista de todos os produtos armazenados no banco de dados.
* Buscar Produtos por Nome: Retorna um produto específico com base no nome fornecido.

* ## 📑 Endpoints
* POST /produtos/api

Busca produtos da Fake Store API e os salva no banco de dados.

* POST /produtos/

Salva um novo produto fornecido no corpo da requisição.

* PUT /produtos/

Atualiza um produto existente com base no ID fornecido.

* DELETE /produtos/

Deleta um produto com base no nome fornecido.

* GET /produtos/

Retorna todos os produtos armazenados no banco de dados.

* GET /produtos/{nome}

Busca um produto específico pelo nome.

## 📝 Configuração e Execução
Requisitos
* Java 17 ou superior
* PostgreSQL
* Gradle 
## Configuração do Banco de Dados
* Crie um banco de dados PostgreSQL.
* Atualize o arquivo application.properties com as credenciais do banco de dados.
```
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco_de_dados
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```
## 📜 Documentação da API

A documentação da API está disponível em:
```
http://localhost:8080/swagger-ui/index.html

```