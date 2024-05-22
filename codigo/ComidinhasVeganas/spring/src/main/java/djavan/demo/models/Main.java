package djavan.demo.models;
import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;


public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        Mesa[] mesas = new Mesa[0];
        
        Cliente cliente = criarCliente(teclado);
        int qtdPessoas = solicitarQuantidadePessoas(teclado);

        // Inicializa a requisição do cliente
        LocalDate entradaCliente = LocalDate.now();
        LocalDate saidaCliente = null; // A data de saída ainda é indefinida
        boolean status = false; // A requisição inicia como não finalizada
        Requisicao requisicao = new Requisicao(int qtdPessoas, Cliente cliente, Mesa mesa);

        // Tenta atribuir uma mesa ao cliente
        for (Mesa mesaAtual : mesas) {
            if (mesaAtual.mesaPodeSerOcupada = true) {
                requisicao.atribuirMesa(mesaAtual);
                requisicao.setMesa(mesaAtual); // Atribui a mesa à requisição
                System.out.println("Mesa " + mesaAtual.getIdMesa() + " atribuída ao cliente " + cliente.getNome());
                break;
            }
        }

        // Menu de opções
        int opcao;
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            
            switch (opcao) {
                case 1:
                    mesas = abrirMesa(teclado); // Adicionar novas mesas
                    break;
                case 2:
                    finalizarRequisicao(requisicao); // Finaliza a requisição e desocupa a mesa
                    break;
                case 3:
                    fecharMesa(mesas); // Fechar uma mesa
                    break;
                case 4:
                    cliente = criarCliente(teclado); // Criar um novo cliente
                    break;
                case 5:
                    verMenu(cardapio); // Ver o menu
                    break;
                case 6:
                    selecionarProduto(teclado, cardapio, requisicao); // Selecionar um produto
                    break;
                case 7:
                    incluirProduto(teclado, requisicao); // Incluir produto no pedido
                    break;
                case 8:
                    fecharConta(requisicao); // Fechar conta
                    break;
                case 9:
                    mostrarConta(requisicao); // Mostrar conta
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
           

        
    }
    /**
     * @param teclado
     * @return
     */
    public static Cliente criarCliente(Scanner teclado) {
        Cliente cliente = new Cliente();
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = teclado.nextLine();
        cliente.setNome(nomeCliente);
        System.out.println("Cliente criado com sucesso: " + cliente.getNome());
        return cliente;
    }

    public static int solicitarQuantidadePessoas(Scanner teclado) {
        System.out.print("Para quantas pessoas será a mesa? ");
        return teclado.nextInt();
    }

    public static Mesa[] abrirMesa(Scanner teclado) {

         // Pergunta ao usuário quantas mesas existem no restaurante
         System.out.print("Quantas mesas existem no restaurante? ");
         int numMesas = teclado.nextInt();
 
         // Cria um array de objetos Mesa com o tamanho fornecido pelo usuário
         Mesa[] mesas = new Mesa[numMesas];
 
         // Preenche as informações de cada mesa
         for (int i = 0; i < numMesas; i++) {
             System.out.println("\nMesa " + (i + 1));
             System.out.print("Capacidade da mesa: ");
             int capacidade = teclado.nextInt();
 
             // Cria a mesa com o ID correspondente a (i + 1)
             mesas[i] = new Mesa(i + 1, capacidade, true);
         }
         return mesas;
    }

    public static void finalizarRequisicao(Requisicao requisicao) {
        if (requisicao.getMesa() != null) {
            requisicao.finalizarReq(requisicao.getMesa()); // Finalizar requisição e desocupar a mesa
            requisicao.setStatus(true); // Atualizar status da requisição
            System.out.println("Requisição finalizada com sucesso.");
        } else {
            System.out.println("Nenhuma mesa atribuída à requisição.");
        }
    }

    public static void fecharMesa(Mesa[] mesas) {
        System.out.println("Fechamento de mesa ainda não implementado.");
    }

    public static void exibirMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Abrir Mesa");
        System.out.println("2. Finalizar Requisição");
        System.out.println("3. Fechar Mesa");
        System.out.println("4. Criar Cliente");
        System.out.println("5. Ver Menu");
        System.out.println("6. Selecionar Produto");
        System.out.println("7. Incluir Produto");
        System.out.println("8. Fechar Conta");
        System.out.println("9. Mostrar Conta");
        System.out.println("0. Sair");
    }

    public static void verMenu(Cardapio cardapio) {
        System.out.println("Pratos:");
        for (Item prato : cardapio.getPratos()) {
            System.out.println(prato.getDescricao() + " - R$ " + prato.precoFinal());
        }
        System.out.println("Bebidas:");
        for (Item bebida : cardapio.getBebidas()) {
            System.out.println(bebida.getDescricao() + " - R$ " + bebida.precoFinal());
        }
    }

    public static void selecionarProduto(Scanner teclado, Cardapio cardapio, Requisicao requisicao) {
        System.out.println("Digite o número do produto para selecionar:");
        // Imprimir produtos do cardápio com índices
        for (int i = 0; i < cardapio.getPratos().length; i++) {
            System.out.println(i + 1 + ". " + cardapio.getPratos()[i].getDescricao());
        }
        for (int i = 0; i < cardapio.getBebidas().length; i++) {
            System.out.println(i + 1 + cardapio.getPratos().length + ". " + cardapio.getBebidas()[i].getDescricao());
        }
        int indiceProduto = teclado.nextInt();
        Item itemSelecionado;
        if (indiceProduto <= cardapio.getPratos().length) {
            itemSelecionado = cardapio.getPratos()[indiceProduto - 1];
        } else {
            itemSelecionado = cardapio.getBebidas()[indiceProduto - cardapio.getPratos().length - 1];
        }
        requisicao.getPedido().adicionarItem(itemSelecionado);
        System.out.println("Produto " + itemSelecionado.getDescricao() + " selecionado.");
    }

    public static void incluirProduto(Scanner teclado, Requisicao requisicao) {
        System.out.print("Digite a descrição do produto: ");
        String descricao = teclado.next();
        System.out.print("Digite o preço do produto: ");
        double preco = teclado.nextDouble();
        Item item = new Item(descricao, preco);
        requisicao.getPedido().adicionarItem(item);
        System.out.println("Produto incluído: " + item.getDescricao() + " - R$ " + item.precoFinal());
    }

    public static void fecharConta(Requisicao requisicao) {
        requisicao.getPedido().fecharPedido();
        System.out.println("Conta fechada. Total a pagar: R$ " + requisicao.getPedido().precoFinal());
    }

    public static void mostrarConta(Requisicao requisicao) {
        System.out.println("Itens consumidos:");
        for (Item item : requisicao.getPedido().getItens()) {
            System.out.println(item.getDescricao() + " - R$ " + item.precoFinal());
        }
        System.out.println("Total: R$ " + requisicao.getPedido().precoFinal());
    }
}
}