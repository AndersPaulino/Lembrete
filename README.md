# Aplicação de Gerenciamento de Lembretes

Esta é uma aplicação de gerenciamento de lembretes que oferece endpoints para interagir com entidades de Pessoa e Lembrete.

## Endpoints

### Pessoa

### Recuperar uma pessoa por ID

#### GET `/api/pessoa/{id}`

Retorna os detalhes de uma pessoa pelo ID fornecido.

##

### Recuperar pessoas por status ativo

#### GET `/api/pessoa/ativo/{ativo}`

Retorna uma lista de pessoas com base no status de ativação fornecido.

##

### Recuperar todas as pessoas

#### GET `/api/pessoa`

Retorna uma lista de todas as pessoas cadastradas.

##

### Cadastrar uma nova pessoa

#### POST `/api/pessoa`

Cadastra uma nova pessoa. Deve ser fornecido um objeto JSON no corpo da solicitação com os detalhes da pessoa.

##

### Atualizar uma pessoa

#### POST `/api/pessoa/{id}`

Atualiza os detalhes de uma pessoa pelo ID fornecido. Deve ser fornecido um objeto JSON no corpo da solicitação com os novos detalhes da pessoa.

##

### Excluir uma pessoa

#### DELETE `/api/pessoa/{id}`

Exclui uma pessoa pelo ID fornecido.

##

### Lembrete

### Recuperar um lembrete por ID

#### GET `/api/lembrete/{id}`

Retorna os detalhes de um lembrete pelo ID fornecido.

##

### Recuperar lembretes por status ativo

#### GET `/api/lembrete/ativo/{ativo}`

Retorna uma lista de lembretes com base no status de ativação fornecido.

##

### Recuperar todos os lembretes

#### GET `/api/lembrete`

Retorna uma lista de todos os lembretes cadastrados.

##

### Cadastrar um novo lembrete

#### POST `/api/lembrete`

Cadastra um novo lembrete. Deve ser fornecido um objeto JSON no corpo da solicitação com os detalhes do lembrete.

##

### Atualizar um lembrete

#### PUT `/api/lembrete/{id}`

Atualiza os detalhes de um lembrete pelo ID fornecido. Deve ser fornecido um objeto JSON no corpo da solicitação com os novos detalhes do lembrete.

##

### Excluir um lembrete

#### DELETE `/api/lembrete/{id}`

Exclui um lembrete pelo ID fornecido.

## Modelos DTO

### LembreteDTO

```yaml
{
      "descricao": "Texto do lembrete"
}
```

### PessoaDTO

```yaml
{
      "nome": "Nome da pessoa",
      "lembreteList": [
            {
                "id": 1
            },
            {
                "id": 2
            },
            {
                "id": 3
            }
      ]
}
```
[Inicio](#aplicação-de-gerenciamento-de-lembretes)<br>
