package djavan.demo.models;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Requisicao {

	private int idRequisicao;
	private int qtdPessoas;
	private LocalDateTime entradaCliente;
	private LocalDateTime saidaCliente;
	private boolean aberta;
	
	private Cliente cliente;
	private Mesa mesa;
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
	public int getIdRequisicao() {
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