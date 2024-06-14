package djavan.demo.models;
import java.util.ArrayList;
import java.util.List;

public class Cardapio {
	
	private Integer id;
	private List<Item> produtos = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public List<Item> getProdutos() {
		return produtos;
	}

	// toString, se necessário
	@Override
	public String toString() {
		return "Cardapio [id=" + id + ", produtos=" + produtos + "]";
	}


}
