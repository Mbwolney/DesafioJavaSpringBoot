## DesafioJavaSpringBoot

Desenvolvido na Linguagem Java junto com Spring Boot.

O Banco de Dados utilizado foi o PostgreSql

## Post - Criar Produto

http://localhost:9999/products

{
    "name":"name",
    "description":"description",
    "price":<preco>
}

## Put - Atualizar Produto

http://localhost:9999/products/1

{
    "id": 1,
    "name": "name",
    "description": "description",
    "price": <preco>
}

## Get - Buscar Produto pelo ID

http://localhost:9999/products/1

{
    "id": 1,
    "name": "name",
    "description": "description",
    "price": <preco>
}

## Delete - Deletar Produto pelo ID

http://localhost:9999/products/

## Get - Listar todos os produtos filtrados de acordo com query parameters passados na URL

q =>  campos name e description
min_price => menor preço
max_price => maior preço

http://localhost:9999/products/search?min_price=1.50&max_price=6.85&q=teste

