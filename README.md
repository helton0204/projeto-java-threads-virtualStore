# 💻 Virtual Store

## 📄 Descrição

O projeto **Virtual Store** é uma aplicação Java baseada em Spring Boot que permite o gerenciamento de estoques, pedidos e produtos. A aplicação oferece funcionalidades para autenticação de usuários, cadastro de produtos, controle de estoque e envio de relatórios e notificações por e-mail de forma assíncrona.

## ⚙️ Funcionalidades

### Estoque

- Listar estoques disponíveis.
- Atualizar quantidades de produtos em estoque.

### Produtos

- Cadastrar novos produtos.
- Listar produtos com paginação.
- Excluir produtos.

### Pedidos

- Cadastrar novos pedidos.
- Enviar e-mails de confirmação após o registro de um pedido.

### Relatórios

- Gerar relatórios sobre o estado do estoque e faturamento obtido.
- Envio agendado de relatórios por e-mail.

## 📂 Estrutura do Projeto

A estrutura do projeto é organizada em pacotes, com cada camada separada:

- `src/main/java` - Contém o código fonte Java
  - `controller` - Gerenciam as requisições HTTP e comunicam-se com os serviços.
  - `service` - Contêm a lógica de negócios e interagem com os repositórios.
  - `repository` - Interfaces para manipulação de dados com Spring Data JPA
  - `model` - Entidades do banco de dados e modelos de dados
  - `dto` - Objetos de Transferência de Dados utilizados nas requisições e respostas.
  - `exception` - Recebe as exceções lançadas e retorna o status correto do erro
- `src/main/resources` - Contém arquivos de configuração
  - `application.properties` - Configurações da aplicação, como banco de dados, conta do e-mail, uso de threads assíncronas e propriedades do Hibernate
  - `db/migration` - Scripts SQL de migração para o Flyway

## 🛠 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot** (Framework principal)
- **Spring Data JPA** (Persistência de dados)
- **Spring Security** (Autenticação e autorização)
- **JavaMailSender** (Envio de e-mails)
- **H2 / MySQL** (Banco de dados)

## 📝 Licença

Projeto desenvolvido por **Helton Oliveira**

---
