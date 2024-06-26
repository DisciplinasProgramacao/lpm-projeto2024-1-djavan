package djavan.demo.models;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = Requisicao.TABLE_NAME)
public class Requisicao extends Throwable {

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

	@Column(name = "valorTotal", length = 20, nullable = false, unique = false)
	private double valorTotal;
	
	@ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
	private Cliente cliente;

	@OneToOne
    @JoinColumn(name = "idMesa", nullable = false)
	private Mesa mesa;

	@OneToOne
    @JoinColumn(name = "idPedido", nullable = false)
	private Pedido pedido;

	@SuppressWarnings({ "unchecked", "rawtypes" })	
	
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

	/**
	 * Define o identificador único da requisição.
	 * @param idRequisicao identificador da requisição.
	 */
	public void setIdRequisicao(Long idRequisicao) {
		this.idRequisicao = idRequisicao;
	}

	public Pedido getPedido() {
		return pedido;
	}

	/**
	 * Retorna a quantidade de pessoas.
	 *
	 * @return a quantidade de pessoas
	 */
	public int getQtdPessoas() {
		return qtdPessoas;
	}

	/**
	 * Retorna a data e hora de entrada do cliente.
	 *
	 * @return a data e hora de entrada do cliente
	 */
	public LocalDateTime getEntradaCliente() {
		return entradaCliente;
	}

	/**
	 * Retorna a data e hora de saída do cliente.
	 *
	 * @return a data e hora de saída do cliente
	 */
	public LocalDateTime getSaidaCliente() {
		return saidaCliente;
	}

	/**
	 * Verifica se está aberta.
	 *
	 * @return true se estiver aberta, false caso contrário
	 */
	public boolean isAberta() {
		return aberta;
	}

	/**
	 * Retorna o cliente.
	 *
	 * @return o cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Retorna a mesa.
	 *
	 * @return a mesa
	 */
	public Mesa getMesa() {
		return mesa;
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
	public void finalizarReq() {
		saidaCliente = LocalDateTime.now();
		aberta = false;
		//pedido.fechar();
		mesa.desocupar();
	}

	/**
	 * Adiciona um pedido à lista de pedidos da requisição.
	 * @param cardapio cardápio do pedido.
	 */
	public Item adicionarItem(Item item) throws Exception {
		pedido.adicionarItem(item);
		for(int i = 0; i <= pedido.getItens().size(); i++){
			if(item.getId() == pedido.getItens().get(i).getId()) return item;
		}
		throw new Exception("Item " + item.getNome() + "nao adicionado.");
	}

	/**
	 * Retorna o valor total do pedido da requisição.
	 * @return valor total.
	 */
	public double calcularValorTotal() {
		return pedido.getValorTotal();
	}

		/**
	 * Calcula o valor da requisição por cliente.
	 * @return valor por cliente.
	 */
	public double calcularValorPorCliente() {
		return pedido.getItens().stream().mapToDouble(Item::getValue).sum();
	}

	public boolean ehDaMesa(int idPassado) {
		if (idPassado == mesa.getIdMesa()) {
			return true;
		}else return false;
	}

	public boolean estahEncerrada() {
		if(aberta == true) return true;
		else return false;
	}
}