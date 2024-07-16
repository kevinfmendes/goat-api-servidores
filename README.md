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
- Deferimento e Indeferimento de Especializações


*Endpoints / URL's*
 
|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/servidores`                                 | POST | Salva o servidor no banco de dados |
|`http://localhost:8080/api/v1/servidores`                                 | GET | Lista todos os servidores presentes no banco de dados |
|`http://localhost:8080/api/v1/servidores/{id}`                             | GET | Busca de servidor baseado no Id salvo no banco de dados |
|`http://localhost:8080/api/v1/servidores/{id}`                             | PUT | Edição de servidor salvo no banco de dados |
|`http://localhost:8080/api/v1/servidores/{id}`                               | DELETE | Deleta cadastro do servidor|
