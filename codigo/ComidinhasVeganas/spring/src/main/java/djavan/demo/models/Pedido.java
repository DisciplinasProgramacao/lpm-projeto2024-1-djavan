package djavan.demo.models;

public class Pedido {
	
	private Cardapio cardapio;

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
