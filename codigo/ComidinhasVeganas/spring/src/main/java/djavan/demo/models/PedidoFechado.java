package djavan.demo.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoFechado extends Pedido {
    
     private List<Item> itensPermitidos;
    private Map<Item, Integer> limitesDeQuantidade;
    private Map<Item, Integer> itensPedido;

    /**
     * Construtor para PedidoFechado.
     * @param itensPermitidos Lista de itens que são permitidos no pedido.
     * @param limitesDeQuantidade Mapa que define o limite de quantidade para cada item.
     */
    public PedidoFechado(List<Item> itensPermitidos, Map<Item, Integer> limitesDeQuantidade) {
        this.itensPermitidos = itensPermitidos;
        this.limitesDeQuantidade = limitesDeQuantidade;
        this.itensPedido = new HashMap<>();
    }

    // Sobrescreve o método setItem para impedir a adição direta de itens.
    @Override
    public void setItem(Item item){
        throw new UnsupportedOperationException("Use o método adicionarItem(Item item, int quantidade) para adicionar itens.");
    }

    /**
     * Adiciona uma quantidade específica de um item ao pedido, respeitando as restrições.
     * @param item O item a ser adicionado.
     * @param quantidade A quantidade do item a ser adicionada.
     */
    public void adicionarItem(Item item, int quantidade) {
        if (!itensPermitidos.contains(item)) {
            throw new IllegalArgumentException("Item " + item.getNome() + " não permitido no pedido fechado.");
        }
        int quantidadeAtual = itensPedido.getOrDefault(item, 0);
        int limite = limitesDeQuantidade.getOrDefault(item, Integer.MAX_VALUE);
        if (quantidadeAtual + quantidade > limite) {
            throw new IllegalArgumentException("Quantidade de " + item.getNome() + " excede o limite permitido.");
        }
        itensPedido.put(item, quantidadeAtual + quantidade);
        for (int i = 0; i < quantidade; i++) {
            super.adicionarItem(item);
        }
    }

    /**
     * Remove uma quantidade específica de um item do pedido.
     * @param item O item a ser removido.
     * @param quantidade A quantidade do item a ser removida.
     */
    public void removerItem(Item item, int quantidade) {
        if (!itensPedido.containsKey(item)) {
            throw new IllegalArgumentException("Item " + item.getNome() + " não está no pedido.");
        }
        int quantidadeAtual = itensPedido.get(item);
        if (quantidade < 0 || quantidadeAtual < quantidade) {
            throw new IllegalArgumentException("Quantidade inválida para remoção.");
        }
        if (quantidadeAtual == quantidade) {
            itensPedido.remove(item);
        } else {
            itensPedido.put(item, quantidadeAtual - quantidade);
        }
        for (int i = 0; i < quantidade; i++) {
            super.removerItem(item);
        }
    }

    /**
     * Obtem os itens e suas quantidades no pedido.
     * @return Mapa dos itens no pedido com suas respectivas quantidades.
     */
    public Map<Item, Integer> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(Map<Item, Integer> itensPedido) {
        this.itensPedido = itensPedido;
    }

    @Override
    public String toString() {
        return "PedidoFechado [itensPermitidos=" + itensPermitidos + ", limitesDeQuantidade=" + limitesDeQuantidade
                + ", itensPedido=" + itensPedido + ", " + super.toString() + "]";
    }
}

