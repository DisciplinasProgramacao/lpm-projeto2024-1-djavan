package djavan.demo.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import djavan.demo.models.Item;
import djavan.demo.models.Mesa;
import djavan.demo.models.Requisicao;
import djavan.demo.repositories.RequisicaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class RequisicaoService {

    @Autowired
    private RequisicaoRepository requisicaoRepository;

/**
     * Encontra uma requisição pelo seu ID.
     * 
     * @param id o ID da requisição.
     * @return a requisição encontrada.
     * @throws RuntimeException se a requisição não for encontrada.
     */
    public Requisicao findById(Long id) {
        Optional<Requisicao> requisicao = this.requisicaoRepository.findById(id);
        return requisicao.orElseThrow(() -> new RuntimeException("Requisição não encontrada!"));
    }

    @Transactional
    public Requisicao create(Requisicao obj){ 
        obj.setIdRequisicao(null);
        obj = this.requisicaoRepository.save(obj);
        return obj;
    } 

    
    /**Método para encerrar uma requisição, chamando o método finalizarReq do model.
    @param requisicaoAEncerrar requisição a ser finalizada.
    @param mesaOcupada mesa referente à requisição.
    @return O objeto requisicaoEncerrada salvo com dados atualizados.*/
    @Transactional
    public Requisicao encerrar(Long id, Mesa mesaOcupada) {
        Optional<Requisicao> optionalRequisicao = requisicaoRepository.findById(id);
        if (!optionalRequisicao.isPresent()) {
            throw new EntityNotFoundException("Requisição não encontrada");
        }
        Requisicao requisicaoEncerrada = optionalRequisicao.get();
        requisicaoEncerrada.finalizarReq(mesaOcupada);
        return requisicaoRepository.save(requisicaoEncerrada);
    }

    @Transactional
    public Requisicao adicionarProduto(Requisicao requisicao, Item itemASerAdicionado){ 
        // Requisicao requisicao = findById(requisicao.getIdRequisicao());
        // requisicao.pedido.adicionarItem(itemASerAdicionado);
        return this.requisicaoRepository.save(requisicao);
    }


}
