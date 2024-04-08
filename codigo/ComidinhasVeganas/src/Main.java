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
        teclado.close();
    }
}
