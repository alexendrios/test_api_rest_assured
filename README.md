# sicredi-desafio-qe

# Sobre
## Este framework foi desenvolvido em Java com Rest Assured com a finalidade de garantir as operações da API referente as seguintes rotas:
>- GET /test
>- GET /users
>- POST /auth/login
>- GET /auth/products
>- POST /products/add
>- GET /products
>- GET /products/{id}

## Estrutura do Projeto

```
SICREDI-DESAFIO-QE
├───src
│   ├───main
│   │   ├───java
│   │   │   └───data
│   │   └───resources
│   └───test
│       ├───java
│       │   ├───runners
│       │   ├───tests
│       │   │   ├───login
│       │   │   │   ├───requests
│       │   │   │   └───tests
│       │   │   ├───products
│       │   │   │   ├───requests
│       │   │   │   └───tests
│       │   │   ├───test
│       │   │   │   ├───requests
│       │   │   │   └───tests
│       │   │   └───user
│       │   │       ├───requests
│       │   │       └───tests
│       │   └───utils
│       └───resources
└───target
    ├───allure-results
    ├───classes
    │   └───data
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    ├───maven-status
    │   └───maven-compiler-plugin
    │       ├───compile
    │       │   └───default-compile
    │       └───testCompile
    │           └───default-testCompile
    ├───surefire-reports
    └───test-classes
        ├───runners
        ├───tests
        │   ├───login
        │   │   ├───requests
        │   │   └───tests
        │   ├───products
        │   │   ├───requests
        │   │   └───tests
        │   ├───test
        │   │   ├───requests
        │   │   └───tests
        │   └───user
        │       ├───requests
        │       └───tests
        └───utils
```

>Setup
- java JDK11
- maven
- allure
- IDE eclipse/IntelliJ
- NODEJS

>Configurar as variáveis de ambiente
- JAVA_HOME
- M2_HOME

>Instalação do allure report
- Certifique que tem o NODE.JS instalado
- Instalar o allure pela linha de comando

```
npm install -g allure-commandline --save-dev
```
## Execução dos Testes

>**Via console**

```
mvn clean test
```
> **pela IDE**
>- Executar a classe TestRunner.java

src

_main

___test

_______java

___________runners

__________________TestRunner.java

>**pela IDE**
>- ***maven***

_____lifecycle

______________clean => duplo click

______________test  => duplo click


## Visualizando os resultado - Report
> terminal (console) apontando onde está o projeto deve - se
```
cd target
allure serve allure-results
```
# Plano de Teste
______________________________________________________________
### ***Funcionalidade: Teste da Aplicação***

> **Cenário: Teste Documentação com Sucesso**
>- Dado que o endpoint de teste é "https://dummyjson.com/test"
>- Quando eu realizo o teste
>- Então a resposta deve ter o status code 200
>- E o corpo da resposta deve conter o campo "status" com o valor "ok"

> **Cenário: Teste Documentação com a URI Inválida**
>- Dado que o endpoint de teste é "https://dummyjson.com/tes"
>- Quando eu realizo o teste
>- Então a resposta deve ter o status code 404
>- E o corpo da resposta deve conter "not found!"

### ***Funcionalidade: Busca de Usuários Cadastrados***

> **Cenário: Busca dos Usuários Cadastrados com Sucesso**
>- Dado que o endpoint de busca de usuários é "https://dummyjson.com/user/"
>- Quando eu realizo a busca de usuários
>- Então a resposta deve ter o status code 200
>- E a resposta deve conter usuários não vazios
>- E a resposta deve ter mais de um usuário
>- E o primeiro usuário deve ter o campo "firstName" não vazio
>- E o primeiro usuário deve ter o campo "email" não nulo
>- E o primeiro usuário deve ter o campo "birthDate" no formato correto

> **Cenário: Busca de Usuário Cadastrado por ID com Sucesso**
>- Dado que o endpoint de busca de usuários é "https://dummyjson.com/user/"
>- Quando eu realizo a busca de usuário pelo ID "100"
>- Então a resposta deve ter o status code 200
>- E a resposta deve ter o campo "users" não vazio
>- E o usuário deve ter o campo "firstName" não vazio
>- E o usuário deve ter o campo "lastName" não vazio
>- E o usuário deve ter o campo "birthDate" no formato correto

> **Cenário: Busca de Usuário Cadastrado por ID - Não Encontrado**
>- Dado que o endpoint de busca de usuários é "https://dummyjson.com/user/"
>- Quando eu realizo a busca de usuário pelo ID "10000"
>- Então a resposta deve ter o status code 404
>- E a resposta deve ter a mensagem "User with id '10000' not found"


### ***Funcionalidade: Autenticação do Usuário***

> **Cenário: Login com Sucesso**
>- Dado que o endpoint de login é "https://dummyjson.com/auth/login"
>- Quando eu faço login com o usuário "kminchelle" e a senha "0lelplR"
>- Então a resposta deve ter o status code 200
>- E o corpo da resposta deve conter o campo "username" com o valor "kminchelle"

> **Cenário: Login com Usuário Inválido**
>- Dado que o endpoint de login é "https://dummyjson.com/auth/login"
>- Quando eu faço login com o usuário "jalapao" e a senha "0lelplR"
>- Então a resposta deve ter o status code 400
>- E o corpo da resposta deve conter a mensagem "Credenciais inválidas"

### ***Funcionalidade: Cadastro e Busca de Produtos***

> **Cenário: Busca de Todos os Produtos Cadastrados com Sucesso**
>- Dado que o endpoint de busca de produtos é "https://dummyjson.com/products/"
>- Quando eu realizo a busca de todos os produtos cadastrados
>- Então a resposta deve ter o status code 200
>- E a resposta deve conter produtos não vazios
>- E o segundo produto deve ter os campos "title", "price", "rating", "stock", "brand" e "category"

> **Cenário: Busca do Produto pelo ID com Sucesso**
>- Dado que o endpoint de busca de produtos é "https://dummyjson.com/products/"
>- Quando eu realizo a busca de um produto pelo ID "50"
>- Então a resposta deve ter o status code 200
>- E o produto deve ter os campos com os valores esperados

> **Cenário: Busca de Produto pelo ID - Não Encontrado**
>- Dado que o endpoint de busca de produtos é "https://dummyjson.com/products/"
>- Quando eu realizo a busca de um produto pelo ID "10000"
>- Então a resposta deve ter o status code 404
>- E a resposta deve ter a mensagem "Product with id '10000' not found"

> **Cenário: Busca de Produtos Cadastrados Autenticado na Aplicação com Sucesso**
>- Dado que o token de autenticação é obtido com usuário "kminchelle" e senha "0lelplR"
>- E o endpoint de busca de produtos autenticado é "https://dummyjson.com/auth/products/"
>- Quando eu realizo a busca de todos os produtos cadastrados
>- Então a resposta deve ter o status code 200
>- E o segundo produto deve ter os campos "title", "price", "rating", "stock", "brand" e "category" com os valores esperados

> **Cenário: Busca de Produtos Cadastrados Autenticado na Aplicação - Token Expirado**
>- Dado que o token de autenticação expirou
>- E o endpoint de busca de produtos autenticado é "https://dummyjson.com/auth/products/"
>- Quando eu realizo a busca de todos os produtos cadastrados
>- Então a resposta deve ter o status code 401
>- E a resposta deve ter a mensagem "Token Expired!"

> **Cenário: Busca de Produtos Cadastrados Autenticado na Aplicação - Token Inválido**
>- Dado que o token de autenticação é inválido
>- E o endpoint de busca de produtos autenticado é "https://dummyjson.com/auth/products/"
>- Quando eu realizo a busca de todos os produtos cadastrados
>- Então a resposta deve ter o status code 500
>- E a resposta deve ter a mensagem "invalid token"

> **Cenário: Busca de Produtos Cadastrados Autenticado na Aplicação - Problema de Autenticação**
>- Dado que o token de autenticação é obtido com usuário "kminchelle" e senha "0lelplR"
>- E o endpoint de busca de produtos autenticado com falha é "https://dummyjson.com/auth/products/"
>- Quando eu realizo a busca de todos os produtos cadastrados
>- Então a resposta deve ter o status code 403
>- E a resposta deve ter a mensagem "Authentication Problem"

> **Cenário: Busca de Produto Cadastrado Autenticado na Aplicação pelo ID com Sucesso**
>- Dado que o token de autenticação é obtido com usuário "kminchelle" e senha "0lelplR"
>- E o endpoint de busca de produtos autenticado é "https://dummyjson.com/auth/products/"
>- Quando eu realizo a busca de um produto pelo ID "50"
>- Então a resposta deve ter o status code 200
>- E o produto deve ter os campos com os valores esperados

> **Cenário: Busca de Produto Cadastrado Autenticado na Aplicação pelo ID - Não Encontrado**
>- Dado que o token de autenticação é obtido com usuário "kminchelle" e senha "0lelplR"
>- E o endpoint de busca de produtos autenticado é "https://dummyjson.com/auth/products/"
>- Quando eu realizo a busca de um produto pelo ID "10000"
>- Então a resposta deve ter o status code 404
>- E a resposta deve ter a mensagem "Product with id '10000' not found"

> **Cenário: Cadastro de um Produto com Sucesso**
>- Dado que o endpoint de cadastro de produtos é "https://dummyjson.com/products/add"
>- Quando eu realizo o cadastro de um produto com sucesso
>- Então a resposta deve ter o status code 201

> **Cenário: Cadastro de um Produto com Sucesso - Verificando se o mesmo está refletindo na aplicação**
>- Dado que o endpoint de cadastro de produtos é "https://dummyjson.com/products/add"
>- Quando eu realizo o cadastro de um produto com sucesso
>- E obtenho o ID do produto cadastrado
>- E o endpoint de busca de produtos é "URL_DO_ENDPOINT"
>- Então a resposta deve ter o status code 200

> **Cenário: Cadastro de um Produto com as Informações Divergentes da Documentação**
>- Dado que o endpoint de cadastro de produtos é "https://dummyjson.com/products/add"
>- Quando eu realizo o cadastro de um produto com informações divergentes da documentação
>- Então a resposta deve ter o status code 400

> **Cenário: Cadastro de um Produto com os Valores das Keys Divergentes da Documentação**
>- Dado que o endpoint de cadastro de produtos é "https://dummyjson.com/products/add"
>- Quando eu realizo o cadastro de um produto com os valores das keys divergentes da documentação
>- Então a resposta deve ter o status code 400

_________________________________________________________________________________________

# Resultados da Execução dos Testes

>Execução

| Quantidade <br/>Casos de Teste | Sucesso | Falhas |
|---------------------------|---------|--------|
| 22                        | 17      | 5      |

# Bugs
## POST /products/add

> 1. Problema Criação de Produto
>- **Status Code:** 200
>- **Status Code Esperado:** 201
>- **Descrição:** Ao cadastrar o produto, deveria retornar um status code 201, mas está retornando 200.

> 2. Problema de Persistência dos Produtos
>- **Status Code:** 200
>- **Status Code Esperado:** 201
>- **Descrição:** Os produtos cadastrados não estão persistindo na aplicação.
>- **Observação:** Mesmo realizando o cadastro autenticado na aplicação, os mesmos não estão refletindo.

> 3. Problemas ao Mudar o Tipo de Dados
>- **Status Code:** 200
>- **Status Code Esperado:** 400
>- **Descrição:** Ao mudar o tipo de dados numérico para booleano, a aplicação está retornando 200.
>- **Observação:** Também, ao mudar todas as chaves (keys), renomeando e mudando o tipo de dados totalmente divergente da documentação, a aplicação está retornando 200.

# Melhorias
> Para realizar o a criação de um produto esse processo deveria ser realizado com o **usuário autenticado**.