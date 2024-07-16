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

Exemplo de Request:
```javascript
{
    "login": "kevin-mendes",
    "password": "123456789",
    "tipo": "PADRAO"
 }
```
Retorno: 
Usuário criado com sucesso

|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/auth/login`                                 | POST | Realiza login do usuário e retorna token que deve ser usado para as demais requisições|

Exemplo de Request:
```javascript
{
    "login": "kevin-mendes",
    "password": "123456789",
}
```
Retorno: 
eyJhbGciOiJIUzI1NiIsIn (exemplo de token)

*Servidor*
|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/servidores`                                 | POST | Salva o servidor no banco de dados |

Exemplo de request: 
```javascript
{
    "cpf": "123.687.987-55",
    "email": "contato@goatsd.com",
    "matricula": "2024301",
    "nome": "Contato Goat",
    "dataNascimento": "1980-01-01",
    "sexo": "Masculino",
    "tipo": "TECNICO"
}
```
Retorno:
```javascript
{
    "id": 2,
    "cpf": "123.687.987-55",
    "email": "contato@goatsd.com",
    "matricula": "2024301",
    "nome": "Contato Goat",
    "dataNascimento": "1980-01-01",
    "sexo": "Masculino",
    "tipo": "TECNICO"
}
```
|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/servidores`                                 | GET | Lista todos os servidores presentes no banco de dados |

Retorno:
```javascript
[
    {
        "id": 1,
        "cpf": "123.687.987-00",
        "email": "danibl26@gmail.com",
        "matricula": "2024201",
        "nome": "Dani Botelho",
        "dataNascimento": "1980-01-01",
        "sexo": "Feminino",
        "tipo": "TECNICO"
    },
    {
        "id": 2,
        "cpf": "123.687.987-55",
        "email": "contato@goatsd.com",
        "matricula": "2024301",
        "nome": "Contato Goat",
        "dataNascimento": "1980-01-01",
        "sexo": "Feminino",
        "tipo": "TECNICO"
    }
]
```
|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/servidores/{id}`                             | GET | Busca de servidor baseado no Id salvo no banco de dados |

Retorno:
```javascript
    {
        "id": 1,
        "cpf": "123.687.987-00",
        "email": "danibl26@gmail.com",
        "matricula": "2024201",
        "nome": "Dani Botelho",
        "dataNascimento": "1980-01-01",
        "sexo": "Feminino",
        "tipo": "TECNICO"
    }
```

|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/servidores/{id}`                             | PUT | Edição de servidor salvo no banco de dados |

Exemplo de request: 
```javascript
{
    "cpf": "123.687.987-55",
    "email": "contato@goatsd.com",
    "matricula": "2024301",
    "nome": "Contato Goat",
    "dataNascimento": "1980-01-01",
    "sexo": "Masculino",
    "tipo": "PROFESSOR"
}
```
Retorno:
```javascript
{
    "id": 2,
    "cpf": "123.687.987-55",
    "email": "contato@goatsd.com",
    "matricula": "2024301",
    "nome": "Contato Goat",
    "dataNascimento": "1980-01-01",
    "sexo": "Masculino",
    "tipo": "PROFESSOR"
}
```

|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/servidores/{id}`                               | DELETE | Deleta cadastro do servidor|

Retorno:
Servidor Deletado com sucesso

*Especialização*
|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/especializacoes`                                 | POST | Salva a especialização no banco de dados|

Exemplo de request: 
```javascript
{
    "area": "Engenharia de Software",
    "tipo": "POS_GRADUACAO",
    "cargaHoraria": 360,
    "valorTotal": 15000.00,
    "servidorId": 1
}
```
Exemplo de Retorno:
```javascript
{
    "id": 4,
    "area": "Engenharia de Software",
    "tipo": "POS_GRADUACAO",
    "cargaHoraria": 360,
    "valorTotal": 15000.0,
    "servidor": {
        "id": 1,
        "cpf": "123.687.987-00",
        "email": "danibl26@gmail.com",
        "matricula": "2024201",
        "nome": "Dani Botelho",
        "dataNascimento": "1980-01-01",
        "sexo": "Feminino",
        "tipo": "TECNICO"
    },
    "status": "PENDENTE",
    "motivoIndeferimento": null
}
```

|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/especializacoes`                                 | GET | Lista todos as especializações presentes no banco de dados |

Exemplo de Retorno:
```javascript
[
    {
        "id": 1,
        "area": "Engenharia de Software",
        "tipo": "POS_GRADUACAO",
        "cargaHoraria": 360,
        "valorTotal": 15000.0,
        "servidor": {
            "id": 1,
            "cpf": "123.687.987-00",
            "email": "danibl26@gmail.com",
            "matricula": "2024201",
            "nome": "Dani Botelho",
            "dataNascimento": "1980-01-01",
            "sexo": "Feminino",
            "tipo": "TECNICO"
        },
        "status": "DEFERIDO",
        "motivoIndeferimento": null
    },
    {
        "id": 2,
        "area": "Agronomia",
        "tipo": "POS_GRADUACAO",
        "cargaHoraria": 360,
        "valorTotal": 15000.0,
        "servidor": {
            "id": 1,
            "cpf": "123.687.987-00",
            "email": "danibl26@gmail.com",
            "matricula": "2024201",
            "nome": "Dani Botelho",
            "dataNascimento": "1980-01-01",
            "sexo": "Feminino",
            "tipo": "TECNICO"
        },
        "status": "INDEFERIDO",
        "motivoIndeferimento": "Falta de documentação necessária"
    },
    {
        "id": 3,
        "area": "Agronomia",
        "tipo": "POS_GRADUACAO",
        "cargaHoraria": 360,
        "valorTotal": 15000.0,
        "servidor": {
            "id": 2,
            "cpf": "123.687.987-55",
            "email": "contato@goatsd.com",
            "matricula": "2024301",
            "nome": "Contato Goat",
            "dataNascimento": "1980-01-01",
            "sexo": "Masculino",
            "tipo": "PROFESSOR"
        },
        "status": "DEFERIDO",
        "motivoIndeferimento": null
    }
]
```

|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/especializacoes/{id}`                             | GET | Busca de especializacao baseado no Id salvo no banco de dados |

Retorno:
```javascript
{
    "id": 1,
    "area": "Engenharia de Software",
    "tipo": "POS_GRADUACAO",
    "cargaHoraria": 360,
    "valorTotal": 15000.0,
    "servidor": {
        "id": 1,
        "cpf": "123.687.987-00",
        "email": "danibl26@gmail.com",
        "matricula": "2024201",
        "nome": "Dani Botelho",
        "dataNascimento": "1980-01-01",
        "sexo": "Feminino",
        "tipo": "TECNICO"
    },
    "status": "PENDENTE",
    "motivoIndeferimento": null
}
```

|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/especializacoes/{id}`                             | PUT | Edição de especializacao salva no banco de dados |

Exemplo de request: 
```javascript
{
    "area": "Engenharia de Software",
    "tipo": "POS_GRADUACAO",
    "cargaHoraria": 370,
    "valorTotal": 20000.00,
    "servidorId": 1
}
```
Exemplo de Retorno:
```javascript
{
    "id": 1,
    "area": "Engenharia de Software",
    "tipo": "POS_GRADUACAO",
    "cargaHoraria": 370,
    "valorTotal": 20000.0,
    "servidor": {
        "id": 1,
        "cpf": "123.687.987-00",
        "email": "danibl26@gmail.com",
        "matricula": "2024201",
        "nome": "Dani Botelho",
        "dataNascimento": "1980-01-01",
        "sexo": "Feminino",
        "tipo": "TECNICO"
    },
    "status": "DEFERIDO",
    "motivoIndeferimento": null
}
```

|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/especializacoes/{id}`                               | DELETE | Deleta cadastro do especilizacao|

Retorno:
Especialização Deletada com sucesso

|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/especializacoes/deferir/{id}`                               | PUT | Defere a especialização pendente e realiza envio de email para o servidor informando o sucesso no deferimento|

Exemplo de Retorno:
```javascript
{
    "id": 1,
    "area": "Engenharia de Software",
    "tipo": "POS_GRADUACAO",
    "cargaHoraria": 370,
    "valorTotal": 20000.0,
    "servidor": {
        "id": 1,
        "cpf": "123.687.987-00",
        "email": "danibl26@gmail.com",
        "matricula": "2024201",
        "nome": "Dani Botelho",
        "dataNascimento": "1980-01-01",
        "sexo": "Feminino",
        "tipo": "TECNICO"
    },
    "status": "DEFERIDO",
    "motivoIndeferimento": null
}
```

|  URL |  Método | Descrição |
|----------|--------------|--------------|
|`http://localhost:8080/api/v1/especializacoes/indeferir/{id}`                               | PUT | Indefere a especialização pendente e realiza envio de email para o servidor informando o motivo do indeferimento|

Exemplo de request:
```javascript
{
  "motivoIndeferimento": "Falta de documentação necessária"
}
```

Exemplo de Retorno:
```javascript
{
    "id": 2,
    "area": "Agronomia",
    "tipo": "POS_GRADUACAO",
    "cargaHoraria": 360,
    "valorTotal": 15000.0,
    "servidor": {
        "id": 1,
        "cpf": "123.687.987-00",
        "email": "danibl26@gmail.com",
        "matricula": "2024201",
        "nome": "Dani Botelho",
        "dataNascimento": "1980-01-01",
        "sexo": "Feminino",
        "tipo": "TECNICO"
    },
    "status": "INDEFERIDO",
    "motivoIndeferimento": "Falta de documentação necessária"
}
```
