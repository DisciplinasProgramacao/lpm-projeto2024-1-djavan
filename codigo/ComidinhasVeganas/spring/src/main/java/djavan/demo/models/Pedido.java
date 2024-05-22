package djavan.demo.models;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.ManyToOne;

public class Pedido {
	
	private Cardapio cardapio;
	
	@ManyToOne
	private Requisicao requisicao;

	public Pedido(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Cardapio getcardapio() {
		return cardapio;
	}

	public void setcardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public double getValor() {
		return cardapio.getValor();
	}

}
