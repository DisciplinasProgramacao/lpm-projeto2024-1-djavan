package djavan.demo.models;


import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.OneToMany;



public class Cardapio {
	
	private Integer id;


	@OneToMany
	private List<Item> itens = new ArrayList<>();

	public Cardapio() {
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Item getProduto(int idprod) {

		return itens.stream().filter(pr -> pr.getId() == id).findFirst().orElse(null);
	}
	
	public List<Item> getCardapio(){
		List<Item> listaItem = new ArrayList<>();
		for (Item x : Item.values()) {
			listaItem.add(x);
		}
		return listaItem;
	}

	public void setItens(List<Item> Itens) {
		this.itens = Itens;
	}


	// toString, se necessário
	@Override
	public String toString() {
		return "Cardapio [id=" + id + ", Itens=" + itens + "]";
	}


}
