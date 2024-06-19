package djavan.demo.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Entidade que representa um pedido no sistema.
 */
@Entity
@Table(name = "pedido")
public class Pedido {
    private Long id;
    private double taxaServico;
    private Item item; // Aparentemente, um pedido pode ter vários itens, mas a classe atual só possui um item. Podemos modificar isso.
    private boolean aberto;
    private Cliente cliente;
    private List<Item> itens; // Mudando de List<Cardapio> para List<Item> para refletir uma coleção de itens específicos no pedido.

    // Construtor padrão
    public Pedido() {
    }

    // Construtor com parâmetros
    public Pedido(Long id, double taxaServico, boolean aberto) {
        this.id = id;
        this.taxaServico = taxaServico;
        this.aberto = aberto;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTaxaServico() {
        return taxaServico;
    }

    public void setTaxaServico(double taxaServico) {
        this.taxaServico = taxaServico;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    // Método para adicionar um item ao pedido
    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    // Método para remover um item do pedido
    public void removerItem(Item item) {
        this.itens.remove(item);
    }

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", taxaServico=" + taxaServico + ", item=" + item + ", aberto=" + aberto
                + ", cliente=" + cliente + ", itens=" + itens + "]";
    }
}

// Subclasse PedidoFechado com restrição de itens e quantidades
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
    public void setItem(Item item) {
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
