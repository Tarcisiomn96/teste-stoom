Olá!

Segue algumas orientações para testes do projeto:

O projeto deve ser rodado através da classe ProdutosAplication.

Ao rodar, uma massa de dados para teste é inserida no banco, que também é criado de maneira automática.

Acesso ao banco: http://localhost:8080/h2-console/

Usuário e senha no arquivo properties.

Banco de dados em memória.

Collection Postman:

    Listar por filtros: curl --location 'http://localhost:8080/produto/listar-por-filtros?ativo=ATIVO&marcaAtiva=ATIVO&categoriaAtiva=ATIVO&nome=PRODUTO&descricao=DESCRI%C3%87%C3%83O&codigo=P010&codigoMarca=M010&codigoCategoria=C006'

    Salvar: curl --location 'http://localhost:8080/produto' \
            --header 'Content-Type: application/json' \
            --data '{
              "nome": "Teste - POST -2",
              "descricao": "Teste - POST -2",
              "idsCategoria": [5,6],
              "idMarca": 4,
              "preco": 0.0
            }'

    Atualizar: curl --location --request PUT 'http://localhost:8080/produto/{id}' \
               --header 'Content-Type: application/json' \
               --data '{
                 "nome": "Teste - PUT -2",
                 "descricao": "Teste - POST -2",
                 "idsCategoria": [],
                 "idMarca": 4,
                 "preco": 0.0
               }'

    Deletar: curl --location --request DELETE 'http://localhost:8080/produto/{id}'

    Inativar: curl --location --request PUT 'http://localhost:8080/produto/inativar/{id}'

    Buscar por id: curl --location 'http://localhost:8080/produto/{id}'

Documentação (swagger):

Obs: Regras de negócio, como validação de campos, não aplicadas.

Ao enviar, um POST ou PUT, os idsCategoria ou idMarca devem existir no banco.

