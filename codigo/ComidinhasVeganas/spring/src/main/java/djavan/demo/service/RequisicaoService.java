package djavan.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import djavan.demo.models.Mesa;
import djavan.demo.models.Requisicao;
import djavan.demo.repositories.RequisicaoRepository;
import jakarta.transaction.Transactional;

@Service
public class RequisicaoService {

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    public List<Requisicao> getAll() {
        return requisicaoRepository.findAll();
    }

    public Requisicao findById(Long id){
        Optional<Requisicao> user = this.requisicaoRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
            "Requisição não encontrada! Id: " + id + ", Tipo: " + Requisicao.class.getName()
        ));
    }

    @Transactional
    public Requisicao create(Requisicao obj){ 
        //obj.setId(null);
        obj = this.requisicaoRepository.save(obj);
        return obj;
    } 

    /**
     * Método para encerrar uma requisição, chamando o método finalizarReq do model.
     * @param requisicaoAEncerrar requisição a ser finalizada.
     * @param mesaOcupada mesa referente à requisição.
     * @return O objeto requisicaoEncerrada salvo com dados atualizados.
     */
    @Transactional
    public Requisicao encerrar(Requisicao requisicaoAEncerrar, Mesa mesaOcupada){ 
        Requisicao requisicaoEncerrada = findById(requisicaoAEncerrar.getIdRequisicao());
        requisicaoEncerrada.finalizarReq(mesaOcupada);
        return this.requisicaoRepository.save(requisicaoEncerrada);
    }
}
