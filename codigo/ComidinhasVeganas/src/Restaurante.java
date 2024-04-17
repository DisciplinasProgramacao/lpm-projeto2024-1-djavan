import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

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
    public Requisicao abrirRequisicao(Cliente cliente, int qtdPessoas){
        Mesa mesaRequisicao = buscarMesa(qtdPessoas);
        
        if (mesaRequisicao == null) {
            Requisicao requisicao = new Requisicao(qtdPessoas, cliente); // requisicao aberta porem sem o horario cliente, qtdPessoas
            fila.add(requisicao);
        }else{
            LocalDate entradaCliente = LocalDate.now();
            Requisicao requisicao = new Requisicao(qtdPessoas, entradaCliente, cliente, mesaRequisicao); // na teoria o criaria a requisicao passando somente o objeto cliente e o numero de pessoas mas o construtor esta com o id e hora de entrada sendo q sao coisas alto geradas
            mesaRequisicao.ocupar();
            requisicoesAtendidas.add(requisicao);
        }
        return null; // ainda nao sei o que retornar
    }

    /**
     * 
     * @param requisicao - passa a requisicao para metodo consigar chamar 
     * o metodo da classe requisicao para fechar trocar o status da mesa para ocupada
     */
    public void finalizarReq(Requisicao requisicao) {
       requisicao.finalizarReq(null);; // nao sei=
       for(int i = 0; i < requisicoesAtendidas.size(); i++){
            if (requisicoesAtendidas.get(i).getIdRequisicao() == requisicao.getIdRequisicao()) {
                pedidosFechados.add(requisicoesAtendidas.get(i));
                requisicoesAtendidas.remove(i);
                break;
            }
       }

    }

    /**
     * 
     * @param mesa - passa a mesa como parametro para que possa verificar se na fila de espera alguma pessoa se adequa a mesa que ficou vazia
     * @return caso ache algum cliente que possa sair da fila de espera ela retorna a requisicao do cliente
     */
    public Mesa verificarFilaEspera(Mesa mesa ){
          for(int i = 0; i < fila.size(); i++){
            Requisicao requisicaoAtual = fila.get(i);
            if(requisicaoAtual.getQtdPessoas() <= mesa.getCapacidade()){
                requisicaoAtual.atribuirMesa(mesa); 
                fila.remove(requisicaoAtual);
            } 
        }
        return null;
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