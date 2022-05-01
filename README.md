# Beer's Recipe

<p align="center">
    <img src="https://www.institutodacerveja.com.br/img/noticias/172.jpg">
</p>

## Projeto criado para cadastro de receitas de cervejas artesanais.
<br/>

### _O projeto foi desenvolvido usando hibernate, onde todo o relacionamento das entidades_
### _foram feitas através do java e assim foi gerado um banco de dados relacional para inserção de dados_
### _tanto em no banco de dados H2 em memória quanto no banco de dados Mysql._


## Cadastrar nova receita

```
POST

/receitas/api/v1
```
## Request

```json
{
    "nomeDaCerveja": "Lucky Red",
    "litros": "20",
    "abv": "4,4",
    "ibu": "22",
    "og": "1044",
    "fg": "1010",
    "cor": "13",
    "familia": {
        "nome": "Irish Red Ale"
    },
    "ingredientes": [
        {
            "ingrediente": "Malte Pilsen",
            "quantidade": "3Kg"
        },
        {
            "ingrediente": "Malte Munique Light",
            "quantidade": "1Kg"
        },
        {
            "ingrediente": "Malte Caramel 150",
            "quantidade": "300g"
        },
        {
            "ingrediente": "Malte Black",
            "quantidade": "60g"
        },
        {
            "ingrediente": "Lupulo Admiral",
            "quantidade": "20g"
        },
        {
            "ingrediente": "Pastilha Whirfloc",
            "quantidade": "1"
        },
        {
            "ingrediente": "Levedura S-04",
            "quantidade": "1"
        }
    ],
    "mostura": {
        "temperaturaRecomendada": "69",
        "tempo": "60",
        "temperaturaInicial": "74",
        "litrosInicial": "20",
        "litrosLavagem": "10",
        "temperaturaLavagem": "70"
    },
    "fervura": [
        {
            "tempo": "60",
            "qtdIngrediente": "10g Admiral"
        },
        {
            "tempo": "30",
            "qtdIngrediente": "#"
        },
        {
            "tempo": "5",
            "qtdIngrediente": "10g Admiral"
        },
        {
            "tempo": "5",
            "qtdIngrediente": "10g Admiral"
        },
        {
            "tempo": "0",
            "qtdIngrediente": "#"
        }
    ],
    "fermentacaoMaturacao": [
        {
            "dia": "0",
            "temperatura": "20"
        },
        {
            "dia": "10",
            "temperatura": "20"
        },
        {
            "dia": "11",
            "temperatura": "5"
        },
        {
            "dia": "14",
            "temperatura": "5"
        }
    ],
    "envase": {
        "qtdPorLitros": "5g",
        "dias": "10"
    }
}

```

## Consultar todas receitas

```
GET

/receitas/api/v1
```

## Consultar receita pelo id

```
GET

/receita/{id}/api/v1
```

## Consultar receita pelo nome

```
GET

/receita/api/v1/{nome}
```

## Atualiazar receita

```
PUT

/receita/{id}/api/v1
```

## Deletar receita pelo id

```
DELETE

/receita/{id}/api/v1
```

## Response

```json
[
  {
    "id": 1,
    "nomeDaCerveja": "Lucky Red",
    "litros": "20",
    "abv": "4,4 %",
    "ibu": "22",
    "og": "1044",
    "fg": "1010",
    "cor": "13",
    "familia": {
      "nome": "Irish Red Ale"
    },
    "mostura": {
      "temperaturaRecomendada": "69",
      "tempo": "60",
      "temperaturaInicial": "74",
      "litrosInicial": "20",
      "litrosLavagem": "10",
      "temperaturaLavagem": "70"
    },
    "envase": {
      "qtdPorLitros": "5g",
      "dias": "10"
    },
    "ingredientes": [
      {
        "ingrediente": "Malte Pilsen",
        "quantidade": "3Kg"
      },
      {
        "ingrediente": "Malte Munique Light",
        "quantidade": "1Kg"
      },
      {
        "ingrediente": "Malte Caramel 150",
        "quantidade": "300g"
      },
      {
        "ingrediente": "Malte Black",
        "quantidade": "60g"
      },
      {
        "ingrediente": "Lupulo Admiral",
        "quantidade": "20g"
      },
      {
        "ingrediente": "Pastilha Whirfloc",
        "quantidade": "1"
      },
      {
        "ingrediente": "Levedura S-04",
        "quantidade": "1"
      }
    ],
    "fervura": [
      {
        "tempo": "60",
        "qtdIngrediente": "10g Admiral"
      },
      {
        "tempo": "30",
        "qtdIngrediente": "#"
      },
      {
        "tempo": "5",
        "qtdIngrediente": "10g Admiral"
      },
      {
        "tempo": "5",
        "qtdIngrediente": "10g Admiral"
      },
      {
        "tempo": "0",
        "qtdIngrediente": "#"
      }
    ],
    "fermentacaoMaturacao": [
      {
        "dia": "0",
        "temperatura": "20"
      },
      {
        "dia": "10",
        "temperatura": "20"
      },
      {
        "dia": "11",
        "temperatura": "5"
      },
      {
        "dia": "14",
        "temperatura": "5"
      }
    ]
  }
]

```

## Desenvolvido usando

## Spring Boot
<p align="center">
    <img src="https://miro.medium.com/max/1400/1*j7q_cdCjUkhHhxJIvrpf1A.png">
</p>

## Hibernate
<p align="center">
    <img src="https://www.alura.com.br/artigos/assets/jpa-hibernate-ou-eclipselink/JPAHibernate.jpg">
</p>

## H2 DataBase
<p align="center">
    <img src="http://www.h2database.com/html/images/connection-mode-embedded-2.png">
</p>

## MySql Database
<p align="center">
    <img src="https://e-tinet.com/wp-content/uploads/2018/10/MySQL-banco-de-dados-linux-1024x512-1-2.png">
</p>

## JacksonApi
<p align="center">
    <img src="https://i1.wp.com/www.topjavatutorial.com/wp-content/uploads/2016/08/Screen-Shot-2016-12-08-at-1.31.45-AM.png">
</p>













