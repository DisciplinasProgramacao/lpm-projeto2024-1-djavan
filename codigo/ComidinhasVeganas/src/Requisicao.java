import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Requisicao {

	private int idRequisicao;
	private int qtdPessoas;
	private LocalDate entradaCliente;
	private LocalDate saidaCliente;
	private boolean status;
	private Cliente cliente;
	LocalDateTime now = LocalDateTime.now();

	public Requisicao() {
	}

	public Requisicao(int idRequisicao, int qtdPessoas, LocalDate entradaCliente, LocalDate saidaCliente, boolean status,
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

	public void setQtdPessoas(int qtdPessoas) throws Exception {
		if (qtdPessoas >= 1){
			this.qtdPessoas = qtdPessoas;
		} else {
			throw new Exception("Insira uma quantidade válida.");
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

	public void setSaidaCliente(LocalDate saidaCliente) throws Exception {
		if (saidaCliente.isAfter(this.entradaCliente)){
			this.saidaCliente = saidaCliente;
		} else {
			throw new Exception("Horario de saida não pode ser menor que o de entrada.");
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
	public void atribuirMesa(Mesa mesa) throws Exception {
		if(mesa.getMesaEstaLivre() && mesa.getCapacidade() >= this.qtdPessoas) {
			
			mesa.ocupar();
            //System.out.println("Mesa " + mesa.getIdMesa() + " atribuída à requisição " + idRequisicao); (Incluir no main após atribuir a mesa com sucesso)
        } else {
			throw new Exception("Não foi possível atribuir a mesa à requisição {" + idRequisicao + "} mesa indisponivel.");
        }
	}
	
	// Desocupar Mesa (Saida do cliente)
	public void finalizarReq(Mesa mesa) throws Exception {

		setSaidaCliente(LocalDate.now());

		mesa.desocupar();
		
	}
}

