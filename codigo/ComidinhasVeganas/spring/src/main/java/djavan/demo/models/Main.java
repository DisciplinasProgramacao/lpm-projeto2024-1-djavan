package djavan.demo.models;
import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;


public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // Menu de opções
        int opcao;
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = teclado.nextInt();
            
            Mesa[] mesas;
            Restaurante restaurante;
            Requisicao requisicao;
            Cardapio cardapio;
            switch (opcao) {
                case 1:
                    mesas = abrirMesa(teclado); // Adicionar novas mesas
                    break;
                case 2:
                    finalizarRequisicao(requisicao); // Finaliza a requisição e desocupa a mesa
                    break;
                case 3:
                    Cliente cliente = criarCliente(teclado); // Criar um novo cliente
                    break;
                case 4:
                    restaurante.exibirCardapio(); // Ver o menu
                    break;
                case 5:
                    selecionarProduto(teclado, requisicao);; // Incluir produto no pedido
                    // tem que passar um produto
                    break;
                case 6:
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
    private static void incluirProduto(Scanner teclado) {
      //perguntar o numero da mesa
      //mostrar cardapio
      //perguntar codigo do produto
      //chamar o metodo do restaurante para incluir produto na mesa
      
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
    } // acho q esse metodo tem que ser colocado na classe restaurante, por mais que ele atualmente nao seja necessario
      // o professor pediu para modificar a parte de criacao de mesa na classe restaurante by Arthur

    public static void finalizarRequisicao(Requisicao requisicao) {
        if (requisicao.getMesa() != null) {
            requisicao.finalizarReq(requisicao.getMesa()); // Finalizar requisição e desocupar a mesa
            System.out.println("Requisição finalizada com sucesso.");
        } else {
            System.out.println("Nenhuma mesa atribuída à requisição.");
        }
    }

    // public static void fecharMesa(Mesa[] mesas) {
    //     System.out.println("Fechamento de mesa ainda não implementado."); 
    // }
    // nao necessario ao iniciar o finalizar uma requisicao o status de uma mesa ja e trocado by Arthur

    public static void exibirMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Abrir Mesa");
        System.out.println("2. Finalizar Requisição");
        System.out.println("3. Fechar Mesa");
        System.out.println("4. Criar Cliente");
        System.out.println("5. Ver Menu");
        System.out.println("6. Fazer Pedido");
        System.out.println("8. Fechar Conta");
        System.out.println("9. Mostrar Conta");
        System.out.println("0. Sair");
    }

    public static void verMenu(Cardapio cardapio) {
        System.out.println();
    }

    public void selecionarProduto(Scanner teclado, Requisicao requisicao) {
        Restaurante.exibirCardapio();
        System.out.println("Digite o número do produto para selecionar:");
        int prod = teclado.nextInt();
        System.out.println("Digite o número da mesa:");
        int mesa = teclado.nextInt(); 
        Restaurante.adicionarProduto(prod, mesa);

    }

    // public static void incluirProduto(Scanner teclado, Requisicao requisicao) {
    //     System.out.print("Digite a descrição do produto: ");
    //     String descricao = teclado.next();
    //     System.out.print("Digite o preço do produto: ");
    //     double preco = teclado.nextDouble();
    //     Item item = new Item(descricao, preco);
    //     requisicao.getPedido().adicionarItem(item);
    //     System.out.println("Produto incluído: " + item.getDescricao() + " - R$ " + item.precoFinal());
    // }

    public static void fecharConta(int idMesa) {
        Requisicao req = restaurante.localizarRequisicao(idMesa);
        Restaurante.finalizarRequisicao(req);
        System.out.println("Conta fechada. Total a pagar: R$ " + requisicao.mostrarConta());
    } // nao necessaria

    public static void mostrarConta(Requisicao requisicao) {
        System.out.println("Itens consumidos:");
        for (Item item : requisicao.getPedido().getItens()) {
            System.out.println(item.getDescricao() + " - R$ " + item.precoFinal());
        }
        System.out.println("Total: R$ " + requisicao.getPedido().precoFinal());
    }
}

