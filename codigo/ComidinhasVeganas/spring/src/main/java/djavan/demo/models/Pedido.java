package djavan.demo.models;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cardapio", nullable = false)
	private Cardapio cardapio;

	@Column(name = "data_pedido", nullable = false)
	private String dataPedido;

	@Column(name = "hora_pedido", nullable = false)
	private String horaPedido;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false, updatable = false)
	private Cliente cliente;

	@Column(name = "approved")
	private Boolean approved;

	@Column(name = "manager_id", nullable = false)
	private String managerId;

	@ManyToOne
	private Requisicao requisicao;

	public Pedido() {
	}

	public Pedido(Long id, String cardapio, String dataPedido, String horaPedido, Cliente cliente, String managerId) {
		this.id = id;
		this.dataPedido = dataPedido;
		this.horaPedido = horaPedido;
		this.cliente = cliente;
		this.managerId = managerId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Cardapio getcardapio() {
		return cardapio;
	}

	public void setcardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public double getValor() {
		return cardapio.getValor();
	}

	public Object getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(Object horaPedido) {
		this.horaPedido = (String) horaPedido;
	}

	public Object getDataPedido() {
		return this.dataPedido = (String) dataPedido;

	}

	public void setDataPedido(Object dataPedido) {
		this.dataPedido = (String) dataPedido;
	}

	public void setCliente(Cliente cliente2) {
		this.cliente = cliente2;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

}
