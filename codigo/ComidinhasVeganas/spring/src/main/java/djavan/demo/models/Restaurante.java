package djavan.demo.models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.READER;

public class Restaurante {
    private ArrayList<Mesa> listaMesa = new ArrayList<>(10);
    private ArrayList<Requisicao> requisicoesAtendidas = new ArrayList<>();
    private ArrayList<Requisicao> fila = new ArrayList<>();
    private ArrayList<Requisicao> pedidosFechados = new ArrayList<>();

    public Restaurante(){
        for(int i = 1; i <= 10; i++){
            if(i < 5)
                listaMesa.add(new Mesa(i, 4, true));  
            else if(i < 9 && i > 4)  
                listaMesa.add(new Mesa(i, 6,true));
            else
                listaMesa.add(new Mesa(i, 8, true));
        }
    }

    public ArrayList<Mesa> getListaMesa() {
        return listaMesa;
    }
    public ArrayList<Requisicao> getRequisicoesAtendidas() {
        return requisicoesAtendidas;
    }
    public ArrayList<Requisicao> getFila() {
        return fila;
    }
    public ArrayList<Requisicao> getPedidosFechados() {
        return pedidosFechados;
    }
    
    /**
    * @param qtdPessoas - busca se a mesa com a quantidade de cadeiras que o cliente precisa esta livre
    * @return caso a a mesa esteja livre retorna a mesa caso nao retorna null
    */
    public Mesa buscarMesa(int qtdPessoas){
        for(Mesa mesa : listaMesa){
            if(mesa.mesaPodeSerOcupada)
                return mesa;
        }
        return null;
    }

    /**
     * @param cliente - para guardar na requisicao
     * @param qtdPessoas - para buscar se a mesa que o cliente deseja esta livre
     * @return retorna a requisicao criada
     */
    public Requisicao abrirRequisicao(Cliente cliente, int qtdPessoas){
        Mesa mesaRequisicao = buscarMesa(qtdPessoas);
        
        if (!mesaRequisicao.mesaPodeSerOcupada) {
            Requisicao requisicao = new Requisicao(qtdPessoas, cliente); 
            fila.add(requisicao);
            return requisicao;
        }else{
            Requisicao requisicao = new Requisicao(qtdPessoas, cliente, mesaRequisicao); 
            mesaRequisicao.ocupar();
            requisicoesAtendidas.add(requisicao);
            return requisicao;
        }
    }

    /**
     * @param requisicao - passa o parametro requisicao fechar o pedido
     * @implNote Chama o metodo da classe requisicao para fechar trocar o status da mesa para ocupada depois chama o metodo verificarFilaEspera para verificar a fila de espera
     */
    public Requisicao finalizarRequisicao(Requisicao requisicao) {
       for(int i = 0; i < requisicoesAtendidas.size(); i++){
            if (requisicoesAtendidas.get(i).getIdRequisicao() == requisicao.getIdRequisicao()) {
                requisicao.finalizarReq(requisicao.getMesa());
                requisicao = requisicoesAtendidas.get(i);
                break;
            }
       }
       mostrarConta(requisicao);
       verificarFilaEspera();
       return requisicao;
    }

    /**    
     * @implNote Apos Finalizar a requisicao esse metodo e chamado para verificar se na lista de espera algum cliente corresponde a mesa que ficou vazia
    */
    public void verificarFilaEspera(){
        for(int i = 0; i < fila.size(); i++){
            Requisicao requisicaoAtual = fila.get(i);
            Mesa mesa = buscarMesa(requisicaoAtual.getQtdPessoas());
            if(mesa == null) break;
            abrirRequisicao(requisicaoAtual.getCliente(), requisicaoAtual.getQtdPessoas());
            fila.remove(fila.get(i));
        }
    }

    /**
     * @param id
     * @return retorna a requisicao pedida
     */
    public Requisicao localizarRequisicao(int id) {
        Requisicao requisicao;
        for(int i = 0; i <= requisicoesAtendidas.size(); i++) {
            if (requisicoesAtendidas.get(i).getIdRequisicao() == id){
                requisicao = requisicoesAtendidas.get(i);
            }
        }
        return requisicao;
    }
    
    /**
     * Esse método itera sobre todos os itens da enumeração "Cardápio" usando o values.
     * Para cada item ele chama o método toString do item. 
     */
    public static void exibirCardapio(){
        Cardapio item = new Cardapio();
        for (int i = 0; i <= item.getProdutos().size(); i++) { 
               System.out.println(item.getProdutos().get(i).toString());
        }
    }

    public void incluirItem(int idProd, int numMesa){
        for(int i = 0; i < requisicoesAtendidas.size(); i++){
            if (requisicoesAtendidas.get(i).getMesa().getIdMesa() == numMesa) {
                requisicoesAtendidas.get(i).adicionarProduto(idProd);
            }
        }
    }

    public static void mostrarConta(Requisicao requisicao) {
        System.out.println("Itens consumidos:");
        for (int i = 0; i <= requisicao.getProdutos().size(); i++) {
            System.out.println(requisicao.getProdutos().get(i).getNome() + " - R$ " + requisicao.getProdutos().get(i).getValue());
        }
        System.out.println("Total: R$ " + requisicao.calcularValorTotal());
    }
}