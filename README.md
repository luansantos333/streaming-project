# __STREAMFLIX__ - Sistema de Streaming em Java
---
### Sobre o Projeto

StreamFlix é uma aplicação de streaming desenvolvida em Java 21 utilizando o framework Spring Boot. Este sistema permite aos usuários assistir a filmes e séries online, com diferentes níveis de acesso e funcionalidades personalizadas.
Funcionalidades Principais

> - *Cadastro e autenticação de usuários*
> - *Catálogo de filmes e séries*
> - *Administração de cátalogo*
> - *Administração de análises dos usuários*
> - *Busca no cátalogo por título e gênero*
> - *Sistema de busca avançado*
> - *Gerenciamento de perfis de usuário*

---
### Níveis de Acesso

- ##### Visitante
    - Visualização limitada do catálogo
    - Acesso à busca básica
- ##### Usuário padrão
    - *Acesso completo ao catálogo*
    - *Possibilidade de adicionar, atualizar ou remover avaliação*
    - *Criação de listas de favoritos*
    - *Busca avançada*
- ##### Administrador
    - *Gerenciamento de usuários*
    - *Adição e remoção de conteúdo*
    - *Acesso a estatísticas e relatórios*
  
---

###  Testando o código

> `git clone https://github.com/luansantos333/streaming-project.git`

Baixe a collection para validar os endpoints [aqui](endpoints/streaming.json)

---
### Tecnologias utilizadas

> - Java 21
> - Spring Boot
> - OAuth2 para autenticação
> - JPA/Hibernate para persistência de dados
> - RESTful API
> - PostgresSQL
> - Docker
---
### Rodando a aplicação com docker
Para rodar a aplicação usando Docker, siga os passos abaixo:
####  1. Construindo a Imagem Docker
- Primeiro, você precisa construir a imagem Docker. Execute o seguinte comando no diretório onde está localizado o seu Dockerfile:
`docker build -t streaming .`

#### 2. Executando o Contêiner

- Após a construção da imagem, você pode executar o contêiner com as variáveis de ambiente necessárias. Use o seguinte comando, substituindo os valores entre colchetes pelos seus dados:

>docker run  --rm -e SPRING_PROFILES_ACTIVE=prod \
-e SPRING_DATASOURCE_USERNAME=[SEU USUÁRIO DO POSTGRES] \
-e SPRING_DATASOURCE_PASSWORD=[SENHA DO USUÁRIO DO POSTGRES] \
-e SPRING_DATASOURCE_URL=[URL DO SEU BANCO DE DADOS] \
-e SPRING_MAIL_HOST=[HOST DO EMAIL] \
-e SPRING_MAIL_PORT=[PORTA DO EMAIL] \
-e SPRING_MAIL_USERNAME=[EMAIL DA APLICAÇÃO] \
-e SPRING_MAIL_PASSWORD=[SENHA DO EMAIL DA APLICAÇÃO] \
--name ${COLOQUE AQUI O NOME DO SEU CONTAINER}

#### Variáveis de Ambiente

As seguintes variáveis de ambiente são necessárias para a configuração da aplicação:

    SPRING_PROFILES_ACTIVE: O perfil ativo da aplicação (ex: prod).

    SPRING_DATASOURCE_USERNAME: O nome de usuário do banco de dados PostgreSQL.

    SPRING_DATASOURCE_PASSWORD: A senha do usuário do banco de dados PostgreSQL.

    SPRING_DATASOURCE_URL: A URL do banco de dados PostgreSQL.

    SPRING_MAIL_HOST: O host do servidor de email.

    SPRING_MAIL_PORT: A porta do servidor de email.

    SPRING_MAIL_USERNAME: O email da aplicação.

    SPRING_MAIL_PASSWORD: A senha do email da aplicação.

#### Exemplo de Uso

Aqui está um exemplo de como você pode executar a aplicação com valores fictícios:

>docker run -e SPRING_PROFILES_ACTIVE=prod \
-e SPRING_DATASOURCE_USERNAME=myuser \
-e SPRING_DATASOURCE_PASSWORD=mypassword \
-e SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/mydatabase \
-e SPRING_MAIL_HOST=smtp.example.com \
-e SPRING_MAIL_PORT=587 \
-e SPRING_MAIL_USERNAME=myapp@example.com \
-e SPRING_MAIL_PASSWORD=myemailpassword \
streaming

Agora sua aplicação streaming deve estar rodando dentro de um contêiner Docker. Você pode acessar a aplicação conforme configurado.
