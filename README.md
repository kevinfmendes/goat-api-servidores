# API Teste Técnico GOAT - Kevin Mendes
API desenvolvida como parte do processo seletivo para vaga de Desenvolvedor Backend Jr. na GOAT Soluções Digitais

## Backend Java
Este projeto é parte do teste técnico para a vaga de Desenvolvedor Backend Jr na empresa GOAT Soluções Digitais. Ele consiste, de maneira resumida, em um backend desenvolvido em Java com Spring Boot e Spring Data JPA, fornecendo endpoints REST para operações CRUD nas entidades de Servidores e Especializações, além de demais funcionalidades.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Security
- JWT
- JavaMailSender
- PostgreSQL
- Mockito / jUnit
- Apache Maven (gerenciador de projetos e dependências)

### Para inicar o projeto:
- Necessário que a máquina possua PostgreSQL instalado e Java JDK 21.
- Ajustar o application.properties para o username e senha definidos no seu banco de dados.

## Funcionalidades
- Cadastro de Servidores / Especializações
- Listagem de Servidores / Especializações por id e completa
- Atualização/Edição de cadastros de Servidores e Especializações por id
- Exclusão de cadastro de Servidores e Especializações pelo seu respectivo id
- Deferimento e Indeferimento de Especializações com envio de email nos casos de deferimento e indeferimento (informando o motivo) para o email cadastrado do servidor

Considerações:
- Além dos requisitos necessários informados na descrição do teste, tomei a liberdade para a criação da entidade de usuário pela necessidade de aplicar a autenticação e autorização das requisições através da abordagem do JWT.
- Na entidade de Especializações adicionei o campo de status para definir o estado da especialização como pendente/deferido/indeferido, além de relacionar as classes Servidor x Especialização, para que a criação de  

*Endpoints / URL's*

*Auth*
|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/auth/register`                                 | POST | Registra novo usuário na aplicação|
|`http://localhost:8080/auth/login`                                 | POST | Realiza login do usuário e retorna token que deve ser usado para as demais requisições|

*Servidor*
|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/servidores`                                 | POST | Salva o servidor no banco de dados |
|`http://localhost:8080/api/v1/servidores`                                 | GET | Lista todos os servidores presentes no banco de dados |
|`http://localhost:8080/api/v1/servidores/{id}`                             | GET | Busca de servidor baseado no Id salvo no banco de dados |
|`http://localhost:8080/api/v1/servidores/{id}`                             | PUT | Edição de servidor salvo no banco de dados |
|`http://localhost:8080/api/v1/servidores/{id}`                               | DELETE | Deleta cadastro do servidor|

*Especialização*
|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/especializacoes`                                 | POST | Salva a especialização no banco de dados|
|`http://localhost:8080/api/v1/especializacoes`                                 | GET | Lista todos as especializações presentes no banco de dados |
|`http://localhost:8080/api/v1/especializacoes/{id}`                             | GET | Busca de especializacao baseado no Id salvo no banco de dados |
|`http://localhost:8080/api/v1/especializacoes/{id}`                             | PUT | Edição de especializacao salva no banco de dados |
|`http://localhost:8080/api/v1/especializacoes/{id}`                               | DELETE | Deleta cadastro do especilizacao|
|`http://localhost:8080/api/v1/especializacoes/deferir/{id}`                               | PUT | Defere a especialização pendente e realiza envio de email para o servidor informando o sucesso no deferimento|
|`http://localhost:8080/api/v1/especializacoes/indeferir/{id}`                               | PUT | Indefere a especialização pendente e realiza envio de email para o servidor informando o motivo do indeferimento|
