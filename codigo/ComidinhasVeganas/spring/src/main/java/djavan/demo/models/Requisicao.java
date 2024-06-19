package djavan.demo.models;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = Requisicao.TABLE_NAME)
public class Requisicao {

	public static final String TABLE_NAME = "requisicao";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRequisicao", unique = true)
	private long idRequisicao;

	@Column(name = "qtd_pessoas", length = 5, nullable = false, unique = false)
	private int qtdPessoas;

	@Column(name = "entrada_cliente", length = 20, nullable = false, unique = false)
	private LocalDateTime entradaCliente;

	@Column(name = "saida_cliente", length = 20, nullable = false, unique = false)
	private LocalDateTime saidaCliente;

	@Column(name = "aberta", length = 20, nullable = false, unique = false)
	private boolean aberta;
	
	@ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
	private Cliente cliente;

	@ManyToOne
    @JoinColumn(name = "idMesa", nullable = false)
	private Mesa mesa;

	@OneToMany(mappedBy = "requisicao")
	private List<Item> produtos = new ArrayList();
	LocalDateTime now = LocalDateTime.now();
	
	public Requisicao(int qtdPessoas, Cliente cliente) {
		this.qtdPessoas = qtdPessoas;
		this.cliente = cliente;
	}

	public Requisicao(int qtdPessoas, Cliente cliente, Mesa mesa) {
		this.qtdPessoas = qtdPessoas;
		this.cliente = cliente;
		this.mesa = mesa;
		entradaCliente = LocalDateTime.now();
	}
	// GETTERS E SETTERS
	public long getIdRequisicao() {
		return idRequisicao;
	}

	public int getQtdPessoas() {
		return qtdPessoas;
	}

	public LocalDateTime getEntradaCliente() {
		return entradaCliente;
	}

	public LocalDateTime getSaidaCliente() {
		return saidaCliente;
	}

	public boolean isAberta() {
		return aberta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public List<Item> getProdutos() {
		return produtos;
	}

	/**
	 * Gera o código hash baseado no identificador da requisição.
	 * @return código hash.
	 */	
	@Override
	public int hashCode() {
		return Objects.hash(idRequisicao);
	}

	/**
	 * Compara esta requisição com outro objeto para verificar igualdade.
	 * @param obj objeto a ser comparado.
	 * @return true se forem iguais, false caso contrário.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Requisicao other = (Requisicao) obj;
		return idRequisicao == other.idRequisicao;
	}

	/**
	 * Atribui e ocupa uma mesa para a requisição, se disponível.
	 * @param mesa mesa a ser ocupada.
	 */
	public void atribuirMesa(Mesa mesa) {
		if (mesa.mesaPodeSerOcupada(qtdPessoas)) {
			mesa.ocupar();
		}
	}

	/**
	 * Finaliza a requisição e libera a mesa ocupada.
	 * @param mesa mesa a ser liberada.
	 */
	public void finalizarReq(Mesa mesa) {
		saidaCliente = LocalDateTime.now();
		aberta = false;
		mesa.desocupar();
	}

	/**
	 * Adiciona um pedido à lista de pedidos da requisição.
	 * @param cardapio cardápio do pedido.
	 */
	public void adicionarProduto(int idProd) {
		Cardapio cardapio = new Cardapio();
		for(int i = 0; i < cardapio.getProdutos().size(); i++){
			if(cardapio.getProdutos().get(i).getId() == idProd){
				produtos.add(cardapio.getProdutos().get(i));
			}
		}
	}

	/**
	 * Calcula o valor total dos pedidos da requisição.
	 * @return valor total.
	 */
	public double calcularValorTotal() {
		return produtos.stream().mapToDouble(c -> produto);
	}
}