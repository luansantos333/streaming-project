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

# Testando o código
### Tecnologias utilizadas
> `git clone https://github.com/luansantos333/streaming-project.git`

> baixe a collection para validar os endpoints [aqui](endpoints/movieflix.json)

---
> - Java 21
> - Spring Boot
> - Spring Security para autenticação
> - JPA/Hibernate para persistência de dados
> - RESTful API
> - PostgresSQL