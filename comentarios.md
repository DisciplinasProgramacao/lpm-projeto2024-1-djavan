# Comentários - Revisão de 12/06 (fim da Sprint 3)

Todos os comentários referem-se ao código do ramo "master" ou "main". É obrigação do grupo manter o código principal neste ramo. Problemas relatados podem ser:

  - ❕❕  - observações e melhorias
  - ⚠️ - médios. Erros de lógica, regras mal implementadas... Descontos de até 1 ponto.
  - 🚨 - sérios. Regras faltando, problemas de modularidade... Descontos de até 3 pontos.
  - 💣 - graves. Falta de classes, requisitos ignorados ... Descontos de 5 ou mais pontos.

  ## Revisão

  - 🚨 Diagrama aparentemente beeeeeem desatualizado.
  - 🚨 main sem compilar por erros de sintaxe/chamadas incorretas.
  - ⚠️ diversas estruturas e chamadas sendo utilizadas que não foram estudadas por nós. Vocês estão entendendo o que estão fazendo?
  - ⚠️ Pedido controller: hora do pedido? E como inserir um produto no pedido?
  - ⚠️ Requisicao controller: alocar mesa? inserir produto?
  - 💣 Cardápio com enum interno. Já comentamos a solução, que não está aqui.
  - ❕❕ O Pedido não deve ter uma referência para a requisição. É o contrário: requisição contém pedido.
  - 🚨 Pedido não deve ter setId (automático), hora (requisito não existente), Cardápio (???), getProdutos (???)
  - 💣 Pedido precisa aceitar produtos individualmente. Ele não tem um "set produto" que recebe um vetor de produtos
  - 🚨 Produto não tem relação com cardápio (???)
  - 💣 Produto precisa ter um preço
  - 🚨 Requisição não precisa ter um getPedido. E está com um método usando classe Item, que não existe.
  - 💣 Restaurante parado na sprint anterior (já sabíamos)

    


