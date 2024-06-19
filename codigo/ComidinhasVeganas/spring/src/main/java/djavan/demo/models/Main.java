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
            Restaurante restaurante = new Restaurante();
            Cardapio cardapio;
            switch (opcao) {
                case 1:
                    finalizarRequisicao(); // Finaliza a requisição e desocupa a mesa
                    break;
                case 2:
                    Cliente cliente = criarCliente(teclado); // Criar um novo cliente
                    break;
                case 3:
                    restaurante.exibirCardapio(); // Ver o menu
                    break;
                case 4:
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
        Cliente cliente = new Cliente();
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = teclado.nextLine();
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
        for(int i = 0; i <= restaurante.getRequisicoesAtendidas().size(); i++){
            Requisicao requisicao = restaurante.getRequisicoesAtendidas().get(i);
            if (requisicao.getMesa().getIdMesa() == idMesa) {
                if (requisicao.getMesa() != null) {
                    requisicao.finalizarReq(requisicao.getMesa()); // Finalizar requisição e desocupar a mesa
                    System.out.println("Requisição finalizada com sucesso.");
                    econtrou = true;
                    break;
                } 
            }
        }
        if (econtrou == false) {
            System.out.println("Nenhuma mesa atribuída à requisição.");
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

        Restaurante.exibirCardapio();
        System.out.println("Digite o número do produto para selecionar:");
        int prod = teclado.nextInt();
        System.out.println("Digite o número da mesa:");
        int mesa = teclado.nextInt(); 
        
        Restaurante restaurante;
        restaurante.incluirItem(prod, mesa);
    }
}   
}

