package djavan.demo.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

/**
 * Classe que representa uma requisição no sistema.
 */
@Entity
public class Requisicao {

	private Long idRequisicao;
	private int qtdPessoas;
	private LocalDate entradaCliente;
	private LocalDate saidaCliente;
	private boolean status;

	private Cliente cliente;
	private Mesa mesa;
	
	@OneToMany(mappedBy = "requisicao")
	private List<Pedido> pedidos = new ArrayList();

	LocalDateTime now = LocalDateTime.now();

	/**
     * Construtor padrão da classe Requisicao.
     */
	public Requisicao() {
	}
	
	/**
     * Construtor da classe Requisicao.
     *
     * @param qtdPessoas Quantidade de pessoas para a reserva.
     * @param cliente Cliente que fez a reserva.
     */
	public Requisicao(int qtdPessoas, Cliente cliente) {
		this.qtdPessoas = qtdPessoas;
		this.cliente = cliente;
	}

	/**
     * Construtor da classe Requisicao.
     *
     * @param qtdPessoas Quantidade de pessoas para a reserva.
     * @param entradaCliente Data de entrada do cliente.
     * @param cliente Cliente que fez a reserva.
     * @param mesa Mesa reservada para o cliente.
     */
	public Requisicao(int qtdPessoas, LocalDate entradaCliente, Cliente cliente, Mesa mesa) {
		this.qtdPessoas = qtdPessoas;
		this.entradaCliente = entradaCliente;
		this.cliente = cliente;
		this.mesa = mesa;
	}

	/**
     * Construtor da classe Requisicao.
     *
     * @param i Identificador da requisição.
     * @param qtdPessoas Quantidade de pessoas para a reserva.
     * @param entradaCliente Data de entrada do cliente.
     * @param saidaCliente Data de saída do cliente.
     * @param status Status da reserva (ativo/inativo).
     * @param cliente Cliente que fez a reserva.
     */

	public Requisicao(int i, int qtdPessoas2, LocalDate entradaCliente2, LocalDate saidaCliente2, boolean status2,
			Cliente cliente2) {
	}

	// Início dos getters e setters
	/**
	 * Retorna o identificador único da requisição.
	 * @return identificador da requisição.
	 */
	public Long getIdRequisicao() {
		return idRequisicao;
	}

	/**
	 * Define o identificador único da requisição.
	 * @param idRequisicao identificador da requisição.
	 */
	public void setIdRequisicao(Long idRequisicao) {
		this.idRequisicao = idRequisicao;
	}

	/**
	 * Retorna a quantidade de pessoas para a reserva.
	 * @return quantidade de pessoas.
	 */
	public int getQtdPessoas() {
		return qtdPessoas;
	}

	/**
	 * Define a quantidade de pessoas para a reserva.
	 * @param qtdPessoas quantidade de pessoas.
	 */
	public void setQtdPessoas(int qtdPessoas) {
		if (qtdPessoas >= 1) {
			this.qtdPessoas = qtdPessoas;
		}
	}

	/**
	 * Retorna a data de entrada do cliente.
	 * @return data de entrada.
	 */
	public LocalDate getEntradaCliente() {
		return entradaCliente;
	}

	/**
	 * Define a data de entrada do cliente.
	 * @param entradaCliente data de entrada.
	 */
	public void setEntradaCliente(LocalDate entradaCliente) {
		this.entradaCliente = entradaCliente;
	}

	/**
	 * Retorna a data de saída do cliente.
	 * @return data de saída.
	 */
	public LocalDate getSaidaCliente() {
		return saidaCliente;
	}

	/**
	 * Define a data de saída do cliente.
	 * @param saidaCliente data de saída.
	 */
	public void setSaidaCliente(LocalDate saidaCliente) {
		if (saidaCliente.isAfter(this.entradaCliente)) {
			this.saidaCliente = saidaCliente;
		}
	}

	/**
	 * Verifica se a reserva está ativa ou não.
	 * @return status da reserva.
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * Define o status da reserva.
	 * @param status status da reserva.
	 */

	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Retorna o cliente associado à requisição.
	 * @return cliente.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Define o cliente associado à requisição.
	 * @param cliente cliente.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Retorna a mesa reservada para o cliente.
	 * @return mesa.
	 */
	public Mesa getMesa() {
		return mesa;
	}

	/**
	 * Define a mesa reservada para o cliente.
	 * @param mesa mesa.
	 */
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	/**
	 * Retorna a lista de pedidos associados à requisição.
	 * @return pedidos.
	 */
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	//fim getters e setters
	
	/**
	 * Adiciona um pedido à lista de pedidos da requisição.
	 * @param cardapio cardápio do pedido.
	 */
	public void adicionarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	/**
	 * Calcula o valor total dos pedidos da requisição.
	 * @return valor total.
	 */
	public double calcularValorTotal() {
		double total = 0.0;
		for (Pedido pedido : pedidos) {
			total += pedido.getValorPedido();
		}
		return total;
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
		if (mesa.mesaPodeSerOcupada) {
			mesa.ocupar();
		}
	}

	/**
	 * Finaliza a requisição e libera a mesa ocupada.
	 * @param mesa mesa a ser liberada.
	 */
	public void finalizarReq(Mesa mesa) {

		setSaidaCliente(LocalDate.now());

		mesa.desocupar();

	}
}