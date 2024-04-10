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

    public Mesa buscarMesa(int qtdPessoas){
        for(Mesa mesa : listaMesa){
            if(mesa.getMesaEstaLivre() && mesa.getCapacidade() >= qtdPessoas)
                return mesa;
        }
        return null;
    }


    public Requisicao abrirRequisicao(Cliente cliente, int qtdPessoas){
        Mesa mesaRequisicao = buscarMesa(qtdPessoas);
        
        if (mesaRequisicao == null) {
            Requisicao requisicao = new Requisicao(cliente, qtdPessoas); // requisicao aberta porem sem o horario
            fila.add(cliente, qtdPessoas);
        }else{
            Requisicao requisicao = new Requisicao(cliente, qtdPessoas, horarioentrada); // na teoria o criaria a requisicao passando somente o objeto cliente e o numero de pessoas mas o construtor esta com o id e hora de entrada sendo q sao coisas alto geradas
            mesaRequisicao.setMesaEstaLivre(false);
            requisicoesAtendidas.add(requisicao);
        }
    }

    public void finalizarReq(Requisicao requisicao) {
        //Requisicao.finalizarReq(requisicao);
       // tem que ter o atributo Mesa porque se n n tem como trocar o status da mesa 
       requisicao.finalizarReq(null);; // nao sei=
       for(int i = 0; i < requisicoesAtendidas.size(); i++){
            if (requisicoesAtendidas[i].getIdRequisicao == requisicao.getIdRequisicao()) {
                pedidosFechados.add(requisicoesAtendidas[i]);
                requisicoesAtendidas.remove(i)
                break;
            }
       }

    }

    public Mesa verificarFilaEspera(int qtdPessoas){// nao sei como aplicar mas sei que precisa existir
        for(int i = 0; i < fila.size(); i++){
            if(fila[i].qtdPessoas == qtdPessoas){
                abrirRequisicao(null, qtdPessoas) // n sei provavelmente q tenho q criar uma outra classe pra receber a requisicao nao sei como fazer
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