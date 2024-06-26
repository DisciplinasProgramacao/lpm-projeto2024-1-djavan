package djavan.demo.models;
import java.util.Scanner;

//criar requisicao
//tentar alocar requisicao
//incluir produto na requisicao
//fechar requisicao e mostrar conta
//deixar escolher entre aberto e fechado

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // Menu de opções
        int opcao;
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(teclado.nextLine());
            
            Mesa[] mesas;
            Restaurante restaurante = new Restaurante();
            Cardapio cardapio;
            switch (opcao) {
                case 4:
                    finalizarRequisicao(); // Finaliza a requisição e desocupa a mesa
                    break;
                case 1:
                    Cliente cliente = criarCliente(teclado); // Criar um novo cliente
                    break;
                case 2:
                    restaurante.getCardapio(); // Ver o menu
                    break;
                case 3:
                    selecionarProduto(); // Incluir produto no pedido
                    // tem que passar um produto
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
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = teclado.nextLine();
        Cliente cliente = new Cliente();
        cliente.setNome(nomeCliente);
        System.out.println("Cliente criado com sucesso: " + cliente.getNome());
        return cliente;
    }

    public static void finalizarRequisicao() {
        Scanner teclado = new Scanner(System.in);
        Restaurante restaurante = new Restaurante();
        boolean econtrou = false;
        System.out.println("Qual o numero da sua Mesa?");
        int idMesa = teclado.nextInt();
        Requisicao requisicao = restaurante.localizarRequisicao(idMesa);
        try{
                requisicao.finalizarReq(); // Finalizar requisição e desocupar a mesa
                System.out.println("Requisição finalizada com sucesso.");
        }catch(NullPointerException ne){
                System.out.println("Não temos requisição na mesa "+idMesa);
        }
         
        
    }

    public static void exibirMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Criar Cliente");
        System.out.println("2. Ver Menu");
        System.out.println("3. Fazer Pedido");
        System.out.println("4. Fechar Conta");
        System.out.println("0. Sair");
    }

    public static void verMenu(Cardapio cardapio) {
        System.out.println();
    }

    public static void selecionarProduto() {
        Scanner teclado = new Scanner(System.in);
        Restaurante restaurante = new Restaurante();
        restaurante.getCardapio();

        System.out.println("Digite o número do produto para selecionar:");
        int prod = teclado.nextInt();
        System.out.println("Digite o número da mesa:");
        int mesa = teclado.nextInt(); 
        
       
        restaurante.acrescentarProduto(prod, mesa);
    }
}

