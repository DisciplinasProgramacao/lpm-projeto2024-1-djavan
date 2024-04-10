import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner teclado = new Scanner(System.in);

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

        // Exibe informações sobre as mesas criadas
        System.out.println("\nMesas criadas:");
        for (int i = 0; i < numMesas; i++) {
            Mesa mesa = mesas[i];
            System.out.println("Mesa " + mesa.getIdMesa() + " - Capacidade: " + mesa.getCapacidade() + " pessoas");
        }
        
        Cliente cliente = new Cliente();
        System.out.print("Digite o ID do cliente: ");
        int idCliente = teclado.nextInt(); // Consome o próximo inteiro como ID
        teclado.nextLine(); // Consume a quebra de linha
        cliente.setIdCliente(idCliente);
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = teclado.nextLine(); // Consome a próxima linha como nome
        cliente.setNome(nomeCliente);
        System.out.println("Cliente criado com sucesso: " + cliente.getNome());
        System.out.print("Para quantas pessoas será a mesa? ");
        int qtdPessoas = teclado.nextInt();

        LocalDate entradaCliente = LocalDate.now(); // Assumimos que o cliente está entrando agora

        // Inicialmente, a saída do cliente não é conhecida, então pode ser definida como null ou a data atual
        LocalDate saidaCliente = null;

        // Status da requisição - assumimos que inicialmente é false
        boolean status = false;

        Requisicao requisicao = new Requisicao(1, qtdPessoas, entradaCliente, saidaCliente, status, cliente);

        // Tenta atribuir uma mesa ao cliente
        for (Mesa mesaAtual : mesas) {
            if (mesaAtual.getMesaEstaLivre() && mesaAtual.getCapacidade() >= qtdPessoas) {
                requisicao.atribuirMesa(mesaAtual);
                System.out.println("Mesa " + mesaAtual.getIdMesa() + " atribuída ao cliente " + cliente.getNome());
                break;
            }
        }

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        teclado.close();
    }
}
