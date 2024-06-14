package djavan.demo.models;
import java.util.List;

public class Pedido {
	private Long id;
	
	private Double valorPedido;
	
	private Requisicao requisicao;
	
	private List<Cardapio> cardapio;

	public Long getId() {
		return this.id;
	}

	public Double getValorPedido() {
		return valorPedido;
	}

	public List<Cardapio> getCardapio() {
		return cardapio;
	}

	public void setCardapio(List<Cardapio> cardapio) {
		this.cardapio = cardapio;
	}

}
