import java.time.LocalDate;
import java.util.Scanner;

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
        Requisicao requisicao = new Requisicao(1, qtdPessoas, entradaCliente, saidaCliente, status, null);

        // Tenta atribuir uma mesa ao cliente
        for (Mesa mesaAtual : mesas) {
            if (mesaAtual.getMesaEstaLivre() && mesaAtual.getCapacidade() >= qtdPessoas) {
                requisicao.atribuirMesa(mesaAtual);
                requisicao.setMesa(mesaAtual); // Atribui a mesa à requisição
                System.out.println("Mesa " + mesaAtual.getIdMesa() + " atribuída ao cliente " + Cliente.getNome());
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
                    cliente = criarCliente(teclado);
                    break;
                case 5:
                    // Opção em branco 2
                    break;
                case 6:
                    // Opção em branco 3
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        
    }

    public static Cliente criarCliente(Scanner teclado) {
        Cliente cliente = new Cliente();
        System.out.print("Digite o ID do cliente: ");
        int idCliente = teclado.nextInt();
        teclado.nextLine(); // Consumir a quebra de linha
        cliente.setIdCliente(idCliente);
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
        System.out.println("5. Opção em branco");
        System.out.println("6. Opção em branco");
        System.out.println("0. Sair");
    }
}