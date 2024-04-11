import java.util.Date;

public class Mesa {
    private int idMesa;
    private int capacidade;
    private boolean mesaEstaLivre;

    /**
    * Construtor da classe Mesa.
    * 
    * @param idMesa Identificador único da mesa.
    * @param capacidade Capacidade máxima de pessoas que a mesa pode acomodar.
    * @param mesaEstaLivre Indica se a mesa está livre ou ocupada.
    */

	public Mesa(int idMesa, int capacidade, boolean mesaEstaLivre) {
		this.idMesa = idMesa;
		this.capacidade = capacidade;
		this.mesaEstaLivre = mesaEstaLivre;
	}

    /**
    * Obtém o identificador único da mesa.
    * 
    * @return O identificador único da mesa.
    */

    public int getIdMesa() {
        return idMesa;
    }
<<<<<<< HEAD
    
    public int getIdMesa() {
		// TODO Auto-generated method stub
		return idMesa;
	}
=======


>>>>>>> e120bb024f385c7dd150b2605143fc3bae406fa3
    /**
    * Obtém a capacidade máxima de pessoas que a mesa pode acomodar.
    * @return A capacidade máxima.
    */
    public int getCapacidade() {
        return capacidade;
    }

    /**
    * Método para ocupar a mesa.
    */
    public void ocupar(){
        if (mesaEstaLivre == false) {
            System.out.println("Esta mesa já está ocupada.");
        } else {
            mesaEstaLivre = false;
            System.out.println("A mesa foi ocupada com sucesso.");
        }
    }

    /**
    * Método para desocupar a mesa.
    */
    public void desocupar(){
        if (mesaEstaLivre == true) {
            System.out.println("Esta mesa já está livre.");
        } else {
            mesaEstaLivre = true;
            System.out.println("A mesa foi desocupada com sucesso.");
        }
    }

	
    
}
