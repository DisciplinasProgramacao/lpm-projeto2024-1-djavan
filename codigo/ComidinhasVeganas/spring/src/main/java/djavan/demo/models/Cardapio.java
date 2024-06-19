package djavan.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cardapio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "cardapio")
	private List<Item> itens = new ArrayList<>();


	public Cardapio() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Item> getItens() {
		return itens;
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
