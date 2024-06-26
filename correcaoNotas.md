# dJAVAn

## Sprint 1 - Até 07/abril
  - Nota de grupo (8 pontos)
    - Modelo UML - restaurante, mesas, requisicoes, cliente (nota de grupo, 8 pontos)
	
  - Nota individual (12 pontos)
    - Implementações e testes + app
    - Documentação das classes.

### Commit 	ee99595 (10/04)
Diagrama - relação Requisicao-Mesa - 7,6

Cliente - construtor vazio, set desnecessário. sem testes. - Laura - 7,2

Mesa - print na classe, sem testes. - Mariana - 7,6

Requisicao - sets desnecessários, sem testes. sem documentação. Variável 'status' - Gabriel - 5

Restaurante - gets desnecessários. sem documentacao. sem testes. - Arthur - 6

Main parte 1 - código "de teste", sem documentacao do processo - Allan - 4

Main parte 2 - sem fazer - Octavio - 0

## Sprint 2 - Até 21/maio
  - Nota de grupo (6 pontos)
    - Modelo UML atualizado - cardápio e pedidos
	- Estrutura Spring
  
  - Nota individual (14 pontos)	
    - Implementações cardápio e pedidos
    - Controllers
    - Correções anteriores

### Commit 84f4059 (aula de 22/05)
Diagrama - métodos para pedidos na requisição e restaurante - Todos - 5

Cardápio - implementação incorreta com enum (os produtos podem ser enum, não o cardápio) - Gabriel

Pedido - pedido tem cardápio (deveria ter uma lista de produtos). set incorreto. calculo valor incorreto - Gabriel - 6

Requisicao - com lista de pedido (falta da classe Item). sets e gets inadequados. Valor é do pedido, não da requisicao - Arthur - 8

Restaurante - sem cardápio. Usando métodos incorretos de mesa. Sem métodos para pedidos. - Octavio - 5

Main - com erro de sintaxe. fazendo gets em série. - Allan - 8,5

Controller Cliente - usa service que não existe - Laura - 7

Estrutura Spring - Mariana 

Controller Mesa - ok, sem operação para alocar/liberar - Mariana - 8

## Sprint 3 - Até 05/junho
  - Nota de grupo (6 pontos)
    - Modelo atualizado - menu fechado
  
  - Nota individual (14 pontos)	
    - Implementações menu fechado e app
    - Correções anteriores
	
### Revisão 12/06
Diagrama sem menu/pedido fechado - Todos - 0 

Main sem compilar por erros de sintaxe/chamadas incorretas. - Allan - 8,4

Pedido controller: hora do pedido? E como inserir um produto no pedido? - Laura - 9

Requisicao controller: alocar mesa? inserir produto? - Mariana - 9

Cardápio com o mesmo problema - Gabriel - 

Pedido com muitos problemas: referência para a requisição, setId, hora, Cardápio, getProdutos - Gabriel - 

Produto não tem relação com cardápio. precisa ter um preço - Gabriel - 0

Requisição não precisa ter um getPedido. E está com um método usando classe Item, que não existe. - Arthur - 7

Restaurante parado na sprint anterior (já sabíamos)