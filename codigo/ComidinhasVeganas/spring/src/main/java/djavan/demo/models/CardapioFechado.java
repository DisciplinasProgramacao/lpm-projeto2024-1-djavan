package djavan.demo.models;

import java.util.List;

public class CardapioFechado extends Cardapio {

    
    private List<Item> itensPermitidos;
	
    public CardapioFechado(List<Item> itensPermitidos) {
        this.itensPermitidos = itensPermitidos;
    }

    @Override
    public void setItens(List<Item> itens) {
        for (Item item : itens) {
            if (!itensPermitidos.contains(item)) {
                throw new IllegalArgumentException("Item " + item.getNome() + " não permitido no cardápio fechado.");
            }
        }
        super.setItens(itens);
    }

    public void adicionarItem(Item item) {
        if (!itensPermitidos.contains(item)) {
            throw new IllegalArgumentException("Item " + item.getNome() + " não permitido no cardápio fechado.");
        }
        getItens().add(item);
    }
}


