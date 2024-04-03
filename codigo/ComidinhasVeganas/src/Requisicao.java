package entities;


import java.util.Date;
import java.util.Objects;

public class Requisicao {

	private int idRequisicao;
	private int qtdPessoas;
	private Date entradaCliente;
	private Date saidaCliente;
	private boolean status;
	private Cliente cliente;

	public Requisicao() {
	}

	public Requisicao(int idRequisicao, int qtdPessoas, Date entradaCliente, Date saidaCliente, boolean status,
			Cliente cliente) {
		super();
		this.idRequisicao = idRequisicao;
		this.qtdPessoas = qtdPessoas;
		this.entradaCliente = entradaCliente;
		this.saidaCliente = saidaCliente;
		this.status = status;
		this.cliente = cliente;
	}

	
	// GETTERS E SETTERS
	public int getIdRequisicao() {
		return idRequisicao;
	}

	public void setIdRequisicao(int idRequisicao) {
		this.idRequisicao = idRequisicao;
	}

	public int getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(int qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}

	public Date getEntradaCliente() {
		return entradaCliente;
	}

	public void setEntradaCliente(Date entradaCliente) {
		this.entradaCliente = entradaCliente;
	}

	public Date getSaidaCliente() {
		return saidaCliente;
	}

	public void setSaidaCliente(Date saidaCliente) {
		this.saidaCliente = saidaCliente;
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

	
	//HASHCODE E EQUALS APENAS COM idRequisição para utilizar de comparação
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
		
	}
	
	public void finalizarReq() {
		
	}
}

