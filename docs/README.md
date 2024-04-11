# Documentação do Projeto

A documentação do projeto é composta pelos seguintes itens: 
 - [Diagramas de classe do projeto (histórico de versões)](/docs/diagramas/) **Obrigatório**
 - [Instruções para uso](/docs/instrucoes.md) **Obrigatório**
 - [Quadro de tarefas do projeto](https://github.com/orgs/DisciplinasProgramacao/projects/186) **Obrigatório para trabalhos em grupo**
 - [Link para o vídeo de apresentação (edite este mesmo md para colocar sua URL)](http://insira.aqui.sua.URL) **Somente em caso de apresentação por vídeo**

Para o "Quadro de Tarefas", o grupo de trabalho deve criar um Projeto no repositório e atualizar o link neste arquivo. Em caso de dúvidas sobre este processo, pergunte ao seu professor com urgência.
A documentação para os requisitos de "Cadastrar cliente" e "Atender cliente/criar requisição" pode ser escrita de forma a detalhar os passos que o programa realiza, bem como explicar a lógica e as decisões de design por trás do código. A seguir está um exemplo de como essa documentação pode ser estruturada:

---

# Documentação dos Requisitos do Sistema

## Requisitos Implementados
- Cadastro de cliente
- Atendimento de cliente e criação de requisição

## 1. Cadastro de Cliente

### Objetivo
Permitir o registro de novos clientes no sistema, armazenando suas informações para futuras requisições.

### Descrição do Processo
Quando um novo cliente precisa ser registrado, o seguinte processo é realizado:

1. **Entrada de Dados**: 
   - Solicita-se ao usuário que forneça um ID único e o nome do cliente.
   - As informações são lidas através da interface de linha de comando.

2. **Criação do Objeto Cliente**:
   - Com os dados fornecidos, uma nova instância da classe `Cliente` é criada.
   - O ID e o nome são configurados para o novo objeto `Cliente` utilizando os métodos `setIdCliente(int idCliente)` e `setNome(String nome)`.

3. **Confirmação**:
   - Após a criação do objeto `Cliente`, uma mensagem de confirmação é exibida, informando que o cliente foi registrado com sucesso.

### Código-Fonte Relacionado
- Classe `Cliente.java`

## 2. Atendimento de Cliente/Criação de Requisição

### Objetivo
Associar clientes registrados a uma mesa disponível no sistema, criando uma requisição que marca a ocupação da mesa.

### Descrição do Processo
Após o cadastro do cliente, uma requisição é criada com os seguintes passos:

1. **Requisição de Dados**:
   - O sistema solicita a quantidade de pessoas para a mesa.
   - A data de entrada do cliente é configurada automaticamente para a data atual.

2. **Criação do Objeto Requisição**:
   - Um objeto `Requisicao` é instanciado com os dados fornecidos e o objeto `Cliente` previamente criado.

3. **Atribuição de Mesa**:
   - O sistema itera sobre as mesas disponíveis e encontra uma mesa livre que possa acomodar a quantidade solicitada de pessoas.
   - O método `atribuirMesa(Mesa mesa)` é chamado no objeto `Requisicao`.

4. **Confirmação**:
   - O sistema confirma que a mesa foi atribuída, exibindo uma mensagem com o ID da mesa e o nome do cliente.

5. **Alteração de Status da Mesa**:
   - O status da mesa selecionada é alterado para "ocupado" utilizando o método `ocupar()` da classe `Mesa`.

### Código-Fonte Relacionado
- Classes `Requisicao.java`, `Mesa.java`, e `Main.java`

### Exceções e Tratamento de Erros
- Se a quantidade de pessoas for menor que 1, uma exceção é lançada informando que a quantidade é inválida.
- Se não houver mesas disponíveis que possam acomodar a quantidade solicitada de pessoas, uma exceção é lançada.

