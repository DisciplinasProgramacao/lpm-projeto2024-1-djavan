package djavan.demo.models;
import java.util.UUID;
public class Cliente {
    private UUID idCliente;
    private String nome;
    
    // construtor default
    public Cliente() {  
    }

	/**
     * Retorna o id do cliente.
     * @return id do cliente.
     */
    public UUID getIdCliente() {
        return idCliente;

    }
    
    /**
     * Retorna o nome do cliente.
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }
	public void setIdCliente(UUID idCliente) {
		this.idCliente = idCliente;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
