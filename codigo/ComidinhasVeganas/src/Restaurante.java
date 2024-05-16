import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private ArrayList<Mesa> listaMesa = new ArrayList<>(10);
    private ArrayList<Requisicao> requisicoesAtendidas = new ArrayList<>();
    private ArrayList<Requisicao> fila = new ArrayList<>();
    private ArrayList<Requisicao> pedidosFechados = new ArrayList<>();

    public Restaurante(){
        listaMesa.add(new Mesa(1, 4, true));
        listaMesa.add(new Mesa(2, 4, true));
        listaMesa.add(new Mesa(3, 4, true));
        listaMesa.add(new Mesa(4, 4, true));
        listaMesa.add(new Mesa(5, 6, true));
        listaMesa.add(new Mesa(6, 6, true));
        listaMesa.add(new Mesa(7, 6, true));
        listaMesa.add(new Mesa(8, 6, true));
        listaMesa.add(new Mesa(9, 8, true));
        listaMesa.add(new Mesa(10, 8, true));
    }

    /**
    * @param qtdPessoas - busca se a mesa com a quantidade de cadeiras que o cliente precisa esta livre
    * @return caso a a mesa esteja livre retorna a mesa caso nao retorna null
    */
    public Mesa buscarMesa(int qtdPessoas){
        for(Mesa mesa : listaMesa){
            if(mesa.getMesaEstaLivre() && mesa.getCapacidade() >= qtdPessoas)
                return mesa;
        }
        return null;
    }

    /**
     * @param cliente - para guardar na requisicao
     * @param qtdPessoas - para buscar se a mesa que o cliente deseja esta livre
     * @return retorna a requisicao criada
     */
    public void abrirRequisicao(Cliente cliente, int qtdPessoas){
        Mesa mesaRequisicao = buscarMesa(qtdPessoas);
        
        if (mesaRequisicao == null) {
            Requisicao requisicao = new Requisicao(qtdPessoas, cliente); 
            fila.add(requisicao);
        }else{
            LocalDate entradaCliente = LocalDate.now();
            Requisicao requisicao = new Requisicao(qtdPessoas, entradaCliente, cliente, mesaRequisicao); 
            mesaRequisicao.ocupar();
            requisicoesAtendidas.add(requisicao);
        }
    }

    /**
     * @param requisicao - passa o parametro requisicao fechar o pedido
     * @implNote Chama o metodo da classe requisicao para fechar trocar o status da mesa para ocupada depois chama o metodo verificarFilaEspera para verificar a fila de espera
     */
    public void finalizarReq(Requisicao requisicao) {
       for(int i = 0; i < requisicoesAtendidas.size(); i++){
            if (requisicoesAtendidas.get(i).getIdRequisicao() == requisicao.getIdRequisicao()) {
                pedidosFechados.add(requisicoesAtendidas.get(i));
                requisicoesAtendidas.remove(i);
                requisicao.finalizarReq(requisicao.getMesa());
                break;
            }
       }
       verificarFilaEspera();
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
        }
    }

    public List<Requisicao> getRequisicoesAguardando() {
        return fila;
    }

    public List<Requisicao> getRequisicoesAtendidas() {
        return requisicoesAtendidas;
    }

    public List<Requisicao> getpedidosFechados() {
        return pedidosFechados;
    }
}