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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data_pedido", nullable = false)
	private String dataPedido;

	@Column(name = "valor_pedido", nullable = false)
	private Double valorPedido;

	@ManyToOne
	private Cliente cliente;

	@Column(name = "manager_id", nullable = false)
	private String managerId;

	@OneToMany
	private List<Produto> produtos;

	public Pedido() {
		this.produtos = new ArrayList<>();
		this.valorPedido = 0.0;
	}

	/**
	 * Construtor da classe Pedido.
	 *
	 * @param id         Identificador único do pedido.
	 * @param dataPedido Data em que o pedido foi realizado.
	 * @param cliente    Cliente que realizou o pedido.
	 * @param managerId  Identificador do gerente responsável pelo pedido.
	 */
	public Pedido(Long id, String dataPedido, Cliente cliente, String managerId) {
		this.id = id;
		this.dataPedido = dataPedido;
		this.cliente = cliente;
		this.managerId = managerId;
		this.produtos = new ArrayList<>();
		this.valorPedido = 0.0;
	}

	/**
	 * Retorna o identificador do pedido.
	 *
	 * @return O identificador único do pedido.
	 */
	public Long getId() {
		return this.id;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	/**
	 * Define a data do pedido.
	 *
	 * @param dataPedido A nova data em que o pedido foi realizado.
	 */
	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	/**
	 * Retorna o valor total do pedido.
	 *
	 * @return O valor total do pedido.
	 */
	public Double getValorPedido() {
		return valorPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Define o cliente do pedido.
	 *
	 * @param cliente O novo cliente do pedido.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getManagerId() {
		return managerId;
	}

	/**
	 * Define o identificador do gerente responsável pelo pedido.
	 *
	 * @param managerId O identificador do gerente responsável pelo pedido.
	 */
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	/**
	 * Retorna a lista de produtos do pedido.
	 *
	 * @return A lista de produtos do pedido.
	 */
	public List<Produto> getProdutos() {
		return produtos;
	}

	/**
	 * Adiciona um produto ao pedido.
	 *
	 * @param produto Produto a ser adicionado ao pedido.
	 */
	public void adicionarProduto(Produto produto) {
		this.produtos.add(produto);
		this.valorPedido += produto.getValue();
	}
}
