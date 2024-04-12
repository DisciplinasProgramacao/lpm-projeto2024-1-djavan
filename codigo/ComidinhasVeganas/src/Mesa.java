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


    /**
    * Obtém a capacidade máxima de pessoas que a mesa pode acomodar.
    * @return A capacidade máxima.
    */
    public int getCapacidade() {
        return capacidade;
    }


    /**
    * Define se a mesa está livre ou ocupada.
    * 
    * @param mesaEstaLivre true se a mesa estiver livre, caso contrário false.
    */
    public void setMesaEstaLivre(boolean mesaEstaLivre) {
        this.mesaEstaLivre = mesaEstaLivre;
    }

    /**
    * Verifica se a mesa está livre.
    * 
    * @return true se a mesa estiver livre, caso contrário false.
    */
    public boolean getMesaEstaLivre() {
        return mesaEstaLivre;
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
