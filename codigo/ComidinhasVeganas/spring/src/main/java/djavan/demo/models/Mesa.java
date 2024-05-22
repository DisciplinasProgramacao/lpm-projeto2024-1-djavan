package djavan.demo.models;

public class Mesa {
    private int idMesa;
    private int capacidade;
    private boolean mesaEstaLivre;
    public boolean mesaPodeSerOcupada;

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
 * Determina se a mesa pode ser ocupada, dada uma quantidade de pessoas. Para a mesa ser ocupada, ela precisa estar livre e sua capacidade precisa ser suficiente.
 * @param quantidadePessoas quantidade de pessoas que desejam sentar na mesa.
 * @return true se a mesa puder ser ocupada (atendendo ao critério de estar livre e ter capacidade suficiente), caso contrário false.
 */
    public boolean mesaPodeSerOcupada(int quantidadePessoas){
        if ((mesaEstaLivre == true) && (capacidade >= quantidadePessoas)){
            return true;
        } else {
            return false;
        }
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
    * Método para ocupar a mesa.
    */
    public boolean ocupar(){
        if (mesaEstaLivre == true) {
            mesaEstaLivre = false;
        }
            return mesaEstaLivre;
    }

    /**
    * Método para desocupar a mesa.
    */
    public boolean desocupar(){
        if (mesaEstaLivre == false) {
            mesaEstaLivre = true;
        }
            return mesaEstaLivre;
    }
    
}
