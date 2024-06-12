package djavan.demo.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidade que representa um pedido no sistema.
 */
@Entity
@Table(name = "pedido")

/**
 * Construtor padrão da classe Pedido.
 */
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data_pedido", nullable = false)
	private String dataPedido;

	@Column(name = "hora_pedido", nullable = false)
	private String horaPedido;
	
	@Column(name = "hora_pedido", nullable = false)
	private Double valorPedido;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false, updatable = false)
	private Cliente cliente;

	@Column(name = "approved")
	private Boolean approved;

	@Column(name = "manager_id", nullable = false)
	private String managerId;

	@ManyToOne
	private Requisicao requisicao;
	
	@ManyToOne
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
	public Pedido(Long id, String cardapio, String dataPedido, String horaPedido, Cliente cliente, String managerId) {
		this.id = id;
		this.dataPedido = dataPedido;
		this.horaPedido = horaPedido;
		this.cliente = cliente;
		this.managerId = managerId;
	}

	
	/**
     * Retorna o identificador do pedido.
     *
     * @return O identificador único do pedido.
     */
	public Long getId() {
		return this.id;
	}

	
	/**
     * Define o identificador do pedido.
     *
     * @param id O novo identificador do pedido.
     */
	public void setId(Long id) {
		this.id = id;
	}

	/**
     * Retorna a hora do pedido.
     *
     * @return A hora em que o pedido foi realizado.
     */
	public Object getHoraPedido() {
		return horaPedido;
	}

	
	/**
     * Define a hora do pedido.
     *
     * @param horaPedido A nova hora em que o pedido foi realizado.
     */
	public void setHoraPedido(Object horaPedido) {
		this.horaPedido = (String) horaPedido;
	}

	
	public Double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(Double valorPedido) {
		this.valorPedido = valorPedido;
	}

	/**
     * Retorna a data do pedido.
     *
     * @return A data em que o pedido foi realizado.
     */
	public Object getDataPedido() {
		return this.dataPedido = (String) dataPedido;

	}

	
	/**
     * Define a data do pedido.
     *
     * @param dataPedido A nova data em que o pedido foi realizado.
     */
	public void setDataPedido(Object dataPedido) {
		this.dataPedido = (String) dataPedido;
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
