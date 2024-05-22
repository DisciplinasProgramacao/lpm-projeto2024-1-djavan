package djavan.demo.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

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

	public Requisicao() {
	}

	public Requisicao(int qtdPessoas, Cliente cliente) {
		this.qtdPessoas = qtdPessoas;
		this.cliente = cliente;
	}

	public Requisicao(int qtdPessoas, LocalDate entradaCliente, Cliente cliente, Mesa mesa) {
		this.qtdPessoas = qtdPessoas;
		this.entradaCliente = entradaCliente;
		this.cliente = cliente;
		this.mesa = mesa;
	}

	public Requisicao(int i, int qtdPessoas2, LocalDate entradaCliente2, LocalDate saidaCliente2, boolean status2,
			Cliente cliente2) {
		// TODO Auto-generated constructor stub
	}

	// GETTERS E SETTERS
	public Long getIdRequisicao() {
		return idRequisicao;
	}

	public void setIdRequisicao(Long idRequisicao) {
		this.idRequisicao = idRequisicao;
	}

	public int getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(int qtdPessoas) {
		if (qtdPessoas >= 1) {
			this.qtdPessoas = qtdPessoas;
		}
	}

	public LocalDate getEntradaCliente() {
		return entradaCliente;
	}

	public void setEntradaCliente(LocalDate entradaCliente) {
		this.entradaCliente = entradaCliente;
	}

	public LocalDate getSaidaCliente() {
		return saidaCliente;
	}

	public void setSaidaCliente(LocalDate saidaCliente) {
		if (saidaCliente.isAfter(this.entradaCliente)) {
			this.saidaCliente = saidaCliente;
		}
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public void adicionarPedido(Cardapio cardapio) {
		Pedido pedido = new Pedido(cardapio);
		pedidos.add(pedido);
	}

	public double calcularValorTotal() {
		double total = 0.0;
		for (Pedido pedido : pedidos) {
			total += pedido.getValor();
		}
		return total;
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

	// Métodos
	public void atribuirMesa(Mesa mesa) {
		if (mesa.mesaPodeSerOcupada) {
			mesa.ocupar();
		}
	}

	// Desocupar Mesa (Saida do cliente)
	public void finalizarReq(Mesa mesa) {

		setSaidaCliente(LocalDate.now());

		mesa.desocupar();

	}
}