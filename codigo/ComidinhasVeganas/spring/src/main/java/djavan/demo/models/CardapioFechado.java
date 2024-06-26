package djavan.demo.models;

import java.util.List;

/**
 * A classe CardapioFechado representa um cardápio específico onde apenas certos itens são permitidos.
 * Esta classe estende a classe Cardapio e adiciona a funcionalidade de restrição de itens.
 */
public class CardapioFechado extends Cardapio {

    /**
     * Lista de itens permitidos neste cardápio.
     */
    private List<Item> itensPermitidos;
	
    /**
     * Construtor para inicializar a lista de itens permitidos.
     * 
     * @param itensPermitidos Uma lista de itens que são permitidos neste cardápio.
     */
    public CardapioFechado(List<Item> itensPermitidos) {
        this.itensPermitidos = itensPermitidos;
    }

    /**
     * Define a lista de itens no cardápio.
     * Apenas itens que estão na lista de itensPermitidos são permitidos.
     * 
     * @param itens A lista de itens a ser definida no cardápio.
     * @throws IllegalArgumentException se algum item da lista não for permitido.
     */
    @Override
    public void setItens(List<Item> itens) {
        for (Item item : itens) {
            if (!itensPermitidos.contains(item)) {
                throw new IllegalArgumentException("Item " + item.getNome() + " não permitido no cardápio fechado.");
            }
        }
        super.setItens(itens);
    }

    /**
     * Adiciona um item ao cardápio.
     * Apenas itens que estão na lista de itensPermitidos são permitidos.
     * 
     * @param item O item a ser adicionado ao cardápio.
     * @throws IllegalArgumentException se o item não for permitido.
     */
    public void adicionarItem(Item item) {
        if (!itensPermitidos.contains(item)) {
            throw new IllegalArgumentException("Item " + item.getNome() + " não permitido no cardápio fechado.");
        }
        // getItens().add(item); //Comentado, pois depende da implementação da classe pai.
    }
}
