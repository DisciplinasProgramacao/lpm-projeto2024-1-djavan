package djavan.demo.models;

public enum Cardapio {

	MOQUECA_DE_PALMITO(1, "32.00", 32.00),
	FALAFEL_ASSADO(2, "20.00", 20.00),
	SALADA_PRIMAVERA_COM_MACARRAO_KONJAC(3, "25.00", 25.00),
	ESCONDIDINHO_DE_INHAME(4, "18.00", 18.00),
	STROGONOFF_DE_cogumelos(5, "35.00", 35.00),
	CACAROLA_DE_LEGUMES(6, "22.00", 22.00),
	
	AGUA(7, "3.00", 3.00),
	COPO_DE_SUCO(8, "7.00", 7.00),
	REFRIGERANTE_ORGANICO(9, "7.00", 7.00),
	CERVEJA_VEGANA(10, "9.00", 9.00),
	TACA_DE_VINHO_VEGANO(11, "18.00", 18.00);
	
	private final int id;
	private final String nome;
	private final Double valor;
	
	private Cardapio(int id, String nome, Double valor) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}


	public Double getValor() {
		return valor;
	}

}
