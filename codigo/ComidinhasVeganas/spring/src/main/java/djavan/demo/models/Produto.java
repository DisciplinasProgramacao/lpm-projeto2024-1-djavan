package djavan.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ManyToMany;

public class Produto {
	
	private String nome;
	
	private List<Cardapio> cardapio = new ArrayList<>();

	@ManyToMany(mappedBy = "produtos")
	private Pedido pedido;
	
	public Produto() {
	}

	public Produto(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cardapio> getCardapio() {
		return cardapio;
	}
	
	public Cardapio getItemCardapioPorID(Integer id){
		return cardapio.get(id);
	}
	
}
