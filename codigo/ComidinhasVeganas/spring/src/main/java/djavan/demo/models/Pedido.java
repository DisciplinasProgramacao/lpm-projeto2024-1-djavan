package djavan.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidade que representa um pedido no sistema.
 */
@Entity
@Table(name = "pedido")
public class Pedido {
	private Long id;
	
	private double taxaServico;
	
	private Item item;
	
	private boolean aberto;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false, updatable = false)
	private Cliente cliente;
  
	@ManyToOne
	private Requisicao requisicao;
	
	private List<Cardapio> cardapio;

	public Pedido() {
	}

	/**
     * Construtor da classe Pedido.
     *
     * @param id Identificador único do pedido.
     * @param cardapio Cardápio associado ao pedido.
     * @param dataPedido Data em que o pedido foi realizado.
     * @param horaPedido Hora em que o pedido foi realizado.
     * @param cliente Cliente que realizou o pedido.
     * @param managerId Identificador do gerente responsável pelo pedido.
     */
	public Pedido(Long id, double taxaServico, boolean aberto) {
		super();
		this.id = id;
		this.taxaServico = taxaServico;
		this.aberto = aberto;
	}

	
	/**
     * Retorna o identificador do pedido.
     *
     * @return O identificador único do pedido.
     */
	public Long getId() {
		return this.id;
	}
	
	public double getTaxaServico() {
		return taxaServico;
	}

	public void setTaxaServico(double taxaServico) {
		this.taxaServico = taxaServico;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	/**
     * Define o cliente do pedido.
     *
     * @param cliente O novo cliente do pedido.
     */
	public void setCliente(Cliente cliente2) {
		this.cliente = cliente2;
	}
	
	/**
     * Retorna o cliente associado ao pedido.
     *
     * @return O cliente que realizou o pedido.
     */
	public Cliente getCliente() {
		return this.cliente;
	}

	
	public List<Cardapio> getCardapio() {
		return cardapio;
	}

	public void setCardapio(List<Cardapio> cardapio) {
		this.cardapio = cardapio;
	}

}
