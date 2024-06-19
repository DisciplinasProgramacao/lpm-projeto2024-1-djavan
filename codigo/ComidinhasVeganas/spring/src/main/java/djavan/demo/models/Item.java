package djavan.demo.models;


public enum Item {
	
	MOQUECA_DE_PALMITO(1, "Moqueca de Palmito", 32.00),
	FALAFEL_ASSADO(2, "Falafel Assado", 20.00),
	SALADA_PRIMAVERA_COM_MACARRAO_KONJAC(3, "Salada Primavera com Macarrão Konjac", 25.00),
	ESCONDIDINHO_DE_INHAME(4, "Escondidinho de Inhame", 18.00),
	STROGONOFF_DE_cogumelos(5, "Strogonoff de Cogumelos", 35.00),
	CACAROLA_DE_LEGUMES(6, "Caçarola de Legumes", 22.00),
	AGUA(7, "Água", 3.00),
	COPO_DE_SUCO(8, "Suco (Copo)", 7.00),
	REFRIGERANTE_ORGANICO(9, "Refrigenrante Orgânico", 7.00),
	CERVEJA_VEGANA(10, "Cerveja Vegana", 9.00),
	TACA_DE_VINHO_VEGANO(11, "Vinho Vegano (Taça)", 18.00);
	
	private Integer id;
	private String nome;
	private Double value;


	Item(Integer id, String nome, Double value) {

		this.id = id;
		this.nome = nome;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", nome=" + nome + ", value=" + value + "]";


	}

}
