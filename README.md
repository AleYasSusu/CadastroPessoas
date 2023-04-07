# cadastroPessoas
API para cadastro de pessoas e endereço

Alessandro Borges de Souza

alessandro.souza@edu.pucrs.br

Link do projeto no Git: https://github.com/AleYasSusu/CadastroPessoas

EndPoints:<br>
É possível testar por meio do swagger basta utilizar a seguinte url
localhost:8080/swaggwe-ui.html

EndPoints:
Buscar Pessoa → GET/ localhost:8080/pessoas/{idPessoa}
Listar Pessoas→ GET/ localhost:8080/pessoas
Salvar Pessoa → POST/ localhost:8080/pessoas
Atualizar Pessoa → PUT/ localhost:8080/pessoas/{idPessoa}

Buscar Endereco → GET/ localhost:8080/enderecos/{idEndereco}
Listar Enderecos → GET/ localhost:8080/enderecos
Buscar Endereco → POST/ localhost:8080/enderecos
Listar Enderecos por Pessoa → GET/ localhost:8080/enderecos/porPessoa/{idPessoa}
Definir Endereco Principal → GET/ localhost:8080/enderecos/definirPrincipal/{idEndereco}
