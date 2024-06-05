package djavan.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = Mesa.TABLE_NAME)
public class Mesa {
    public static final String TABLE_NAME = "mesa";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int idMesa;
    
    @Column(name = "capacidade", length = 60, nullable = false)
    private int capacidade;

    @Column(name = "mesaEstaLivre", length = 60, nullable = false)
    private boolean mesaEstaLivre;

    @Column(name = "mesaPodeSerOcupada", length = 60, nullable = false)
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

    /**
     * Método para setar o identificador único da mesa. 
     * @param idMesa
     */
    public void setId(int idMesa) {
        this.idMesa = idMesa;
    }
    
}
