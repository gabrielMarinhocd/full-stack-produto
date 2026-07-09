# 💻 Sistema de Cadastro de Notebooks

Sistema desenvolvido com **Spring Boot** no backend e **Angular 17 + Angular Material** no frontend para gerenciamento de notebooks e seus acessórios.

---

# 🚀 Tecnologias

## 🔙 Backend

- ☕ Java 21
- 🌱 Spring Boot 3.5
- 📦 Spring Data JPA
- 🗄️ PostgreSQL / H2
- 📖 SpringDoc OpenAPI (Swagger)

## 🎨 Frontend

- 🅰️ Angular 17
- 🎨 Angular Material
- 📡 HttpClient
- 📝 Reactive Forms
- 🔀 Angular Router

---

# 📂 Estrutura do Projeto

```
projeto/
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── br/com/gabrielmsantos/produto/
│   │   │   │       ├── config/
│   │   │   │       ├── controller/
│   │   │   │       ├── entity/
│   │   │   │       ├── repository/
│   │   │   │       ├── service/
│   │   │   │       └── ProdutoApplication.java
│   │   │   └── resources/
│   │   └── test/
│   ├── pom.xml
│   └── README.md
│
└── frontend/
    ├── src/
    │   ├── app/
    │   │   ├── features/
    │   │   │   └── notebooks/
    │   │   │       ├── models/
    │   │   │       ├── pages/
    │   │   │       │   ├── cadastro/
    │   │   │       │   └── lista/
    │   │   │       └── notebook.service.ts
    │   │   ├── app.config.ts
    │   │   ├── app.routes.ts
    │   │   └── app.component.ts
    │   ├── assets/
    │   ├── styles.scss
    │   └── main.ts
    ├── package.json
    └── README.md
```

---

# ⚙️ Pré-requisitos

Antes de executar o projeto, instale:

- ✅ Java 21
- ✅ Maven 3.9+
- ✅ Node.js 18+
- ✅ Angular CLI

Instalação do Angular CLI:

```bash
npm install -g @angular/cli
```

---

# 🗄️ Banco de Dados

O projeto suporta:

- 🟢 PostgreSQL
- 🟢 H2 Database

Configure o banco de dados no arquivo:

```
backend/src/main/resources/application.properties
```

---

# ▶️ Executando o Backend

Entre na pasta:

```bash
cd backend
```

Execute:

```bash
mvn spring-boot:run
```

Ou execute diretamente pela IDE iniciando a classe:

```
ProdutoApplication.java
```

Após iniciar, a API ficará disponível em:

```
http://localhost:8080
```

## 📖 Documentação da API

A API está documentada utilizando **SpringDoc OpenAPI (Swagger)**.

Após iniciar o backend, acesse:

```
http://localhost:8080/swagger-ui/index.html
```

Lá é possível:

- 📌 Visualizar todos os endpoints disponíveis;
- 📥 Consultar os modelos de requisição e resposta;
- ▶️ Executar chamadas diretamente pela interface do Swagger;
- 📄 Ver os códigos de retorno e descrições das operações.

# ▶️ Executando o Frontend

Entre na pasta:

```bash
cd frontend
```

Instale as dependências:

```bash
npm install
```

Inicie a aplicação:

```bash
ng serve
```

ou

```bash
npm start
```

A aplicação ficará disponível em:

```
http://localhost:4200
```

---

# 🧪 Executando os Testes

## Backend

```bash
cd backend

mvn test
```

---

## Frontend

```bash
cd frontend

ng test
```

---

# 📌 Funcionalidades

✅ Cadastro de Notebook

✅ Alteração de Notebook

✅ Exclusão de Notebook

✅ Consulta de Notebook

✅ Cadastro de acessórios

✅ Remoção de acessórios

✅ Validação de formulários

✅ Documentação da API com Swagger

---

# 🌐 Fluxo da Aplicação

```
                👤 Usuário
                    │
                    ▼
        Angular 17 + Material
                    │
          HTTP (REST API)
                    │
                    ▼
         Spring Boot 3.5 REST
                    │
            Spring Data JPA
                    │
                    ▼
         PostgreSQL / H2 Database
```

---

# 📦 Principais Dependências

## Backend

- Spring Boot Web
- Spring Data JPA
- PostgreSQL
- H2 Database
- SpringDoc OpenAPI

## Frontend

- Angular
- Angular Material
- Reactive Forms
- RxJS

---

## Nota Técnica

Você pode responder de forma objetiva e profissional, por exemplo:

---

## 📝 Nota Técnica

Durante o desenvolvimento, foi adotado o **Spring Boot** no backend e o **Angular 17 com Angular Material** no frontend, por serem tecnologias consolidadas que proporcionam produtividade, organização e facilidade de manutenção.

No backend, a aplicação foi estruturada em camadas (**Controller**, **Service** e **Repository**), seguindo o princípio de separação de responsabilidades. Além disso, foi implementado um tratamento global de exceções para padronizar as respostas da API, tornando o consumo pelo frontend mais consistente.

No frontend, foram utilizados **Reactive Forms**, que oferecem maior controle sobre validações e manipulação dos dados dos formulários. A organização dos componentes por funcionalidades também contribui para uma arquitetura mais modular e facilita futuras manutenções.

### ⚖️ Decisões e Trade-offs

Como decisão de implementação, os acessórios são adicionados e manipulados localmente no frontend durante o cadastro do notebook, sendo enviados à API apenas no momento da persistência. Essa abordagem simplifica o fluxo da aplicação e reduz a quantidade de requisições ao servidor. Em cenários mais complexos, o gerenciamento dos acessórios pode ser realizado por meio de endpoints específicos.

### 🚀 Melhorias Futuras

- 🔐 Implementar autenticação e autorização utilizando **Spring Security** e **JWT**.
- 📄 Adicionar paginação, ordenação e filtros nas consultas.
- 🧪 Ampliar a cobertura de testes unitários e incluir testes de integração.
- 🐳 Containerizar a aplicação com **Docker** e **Docker Compose**.
- ⚙️ Configurar uma pipeline de **CI/CD** para automatizar testes e deploy.
- 💬 Melhorar a experiência do usuário com notificações, indicadores de carregamento e confirmações de ações.

### ✅ Considerações Finais

As decisões adotadas priorizaram a simplicidade da solução, a organização do código e a facilidade de manutenção, resultando em uma aplicação preparada para receber novas funcionalidades e evoluções futuras.


# 👨‍💻 Autor

Gabriel Marinho 

Java • Spring Boot • Angular
