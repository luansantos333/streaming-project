# Use a imagem base do OpenJDK
FROM openjdk:21

### INSTRUÇÕES PARA EXECUÇÃO ###
# Para executar esta aplicação em produção, você precisará executar o seguinte comando:
# docker run -e SPRING_PROFILES_ACTIVE=prod \
#            -e SPRING_DATASOURCE_USERNAME=[SEU USUÁRIO DO POSTGRES] \
#            -e SPRING_DATASOURCE_PASSWORD=[SENHA DO USUÁRIO DO POSTGRES] \
#            -e SPRING_DATASOURCE_URL=[URL DO SEU BANCO DE DADOS] \
#            -e SPRING_MAIL_HOST=[HOST DO EMAIL] \
#            -e SPRING_MAIL_PORT=[PORTA DO EMAIL] \
#            -e SPRING_MAIL_USERNAME=[EMAIL DA APLICAÇÃO] \
#            -e SPRING_MAIL_PASSWORD=[SENHA DO EMAIL DA APLICAÇÃO]

# Você também pode executar com algumas alterações, se desejar:
# - SECURITY_JWT_DURATION: Adicione esta variável de ambiente para alterar a duração do JWT em segundos.
# - SECURITY_CLIENT_ID: Altere o ID da sua aplicação.
# - SECURITY_CLIENT_SECRET: Altere o segredo da sua aplicação.

# Crie o diretório da aplicação
RUN mkdir -p /usr/src/myapp

# Copie o JAR da aplicação para o diretório
COPY streaming-0.0.1-SNAPSHOT.jar /usr/src/myapp

# Defina o diretório de trabalho
WORKDIR /usr/src/myapp

# LINK TO REPO
LABEL org.opencontainers.image.source https://github.com/luansantos333/streaming-project

# Crie um usuário não root
RUN useradd -ms /bin/bash java

# Execute como o usuário não root
USER java

# Defina o perfil padrão
ENV SPRING_PROFILES_ACTIVE=profile

# Comando para executar a aplicação
CMD ["java", "-jar", "streaming-0.0.1-SNAPSHOT.jar"]

