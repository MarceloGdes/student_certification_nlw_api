
# Student Certification API
Student Certification é uma API Restfull criada apartir da NLW (Next Level Week) - Expert proporcionada pela Rocketseat com o objetivo de ensinar Java com Spring Framework entre outras bibliotecas.
Até o momento fizemos funcionalidades basicas sem muitas validações de campos, mas tenho o intuito de continuar a progreção deste app.

![Logo](https://tse2.mm.bing.net/th/id/OIG2.U.2v0fs2o7.uJkqoJObi?pid=ImgGn)
*Imagem criada com IA*


## Funcionalidades

- Validação se o estudante ja fez determinada certificação
- Retorno de uma lista de questões para cada tecnologia 
- Função de resposta das questões com validação se a certificação foi tirada ou não
- Retorno de lista de ranking de notas


## Stack utilizada

**Back-end:** Java17, Spring Framework, Maeven, Lombok
**Banco de Dados:** PostgreSQL, Docker


## Ferramentas Nescessarias

- [Java SE 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Docker](https://docs.docker.com/desktop/install/mac-install/)


## Variáveis de Ambiente

- Você pode alterar a porta do servidor local no arquivo `application.properties`, na propriedade `server.port`

- Variaveis do banco de dados podem ser alteradas no `docker-compose.yaml` mas devem atualizadas no `application.properties` e no `CreateSeed.java`



## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/MarceloGdes/student_certification_nlw_api.git
```

Entre no diretório do projeto

```bash
  cd student_certification_nlw_api
```

Rode o docker-compose com o Docker aberto em seu computador

```bash
  docker-compose up -d
```

Rode a aplicação para ser criada as tabelas dentro do banco de dados.

```bash
  mvn spring-boot:run
```

Pare a aplicação e injete os dados iniciais do banco de dados rodadando o `CreateSeed.java`

Rode novvamente a aplicação

```bash
  mvn spring-boot:run
```


## Documentação da API

#### Verifica se o usuário ja tem a certificação

```http
  POST /students/verifyIfHasCertification
```

```json
BODY
{
	"email": "teste@email.com",
	"technology": "JAVA"
}
```


#### Retorna todas as questões com as alternativas

```http
  GET /questions/technology/JAVA
```



#### Responder as questões
```http
  POST /students/certification/answer
```

```json
BODY
{
	"email": "teste@email.com",
	"technology": "JAVA",
	"questionsAnswers": [
		
		{
			"questionID": "c5f02721-6dc3-4fa6-b46d-6f2e8dca9c66",
			"alternativeID": "bafdf631-6edf-482a-bda9-7dce1efb1890"
		},
		{
			"questionID": "b0ec9e6b-721c-43c7-9432-4d0b6eb15b01",
			"alternativeID": "f8e6e9b3-199b-4f0d-97ce-7e5bdc080da9"
		},
		{
			"questionID": "f85e9434-1711-4e02-9f9e-7831aa5c743a",
			"alternativeID": "e4dbf524-0a54-428a-b57c-853996fc8e19"
		}
	]
}
```


#### Retorna um Ranking das top 10 certificações por nota

```http
  GET /ranking/top10
```

