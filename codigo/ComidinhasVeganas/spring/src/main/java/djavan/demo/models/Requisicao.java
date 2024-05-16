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
	private List<Pedido> pedidos = new ArrayList();

	LocalDateTime now = LocalDateTime.now();
	
	public Requisicao(int qtdPessoas, Cliente cliente) {
		this.qtdPessoas = qtdPessoas;
		this.cliente = cliente;
	}

	public Requisicao(int qtdPessoas, Cliente cliente, Mesa mesa) {
		this.qtdPessoas = qtdPessoas;
		this.entradaCliente = entradaCliente;
		this.cliente = cliente;
		this.mesa = mesa;
		saidaCliente = LocalDateTime.now();
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	// HASHCODE E EQUALS APENAS COM idRequisição para utilizar de comparação
	@Override
	public int hashCode() {
		return Objects.hash(idRequisicao);
	}

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

	// METODOS

	public void atribuirMesa(Mesa mesa) {
		if (mesa.mesaPodeSerOcupada(qtdPessoas)) {
			mesa.ocupar();
		}
	}

	// Desocupar Mesa (Saida do cliente)
	public void finalizarReq(Mesa mesa) {
		saidaCliente(LocalDate.now());
		aberta = false;
		mesa.desocupar();
	}

	public void adicionarPedido(Produtos produto) {
		Pedido pedido = new Pedido(produto);
		pedidos.add(pedido);
	}

	public double calcularValorTotal() {
		double total = 0.0;
		for (Pedido pedido : pedidos) {
			total += pedido.getValor();
		}
		return total;
	}
}