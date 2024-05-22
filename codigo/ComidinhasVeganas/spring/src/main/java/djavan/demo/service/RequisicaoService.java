// ClientService.java
package djavan.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import djavan.demo.models.Cliente;
import djavan.demo.models.Requisicao;
import djavan.demo.repositories.RequisicaoRepository;
import jakarta.validation.Valid;




@Service
public class RequisicaoService {
    @Autowired
    private RequisicaoRepository requisicaoRepository;

    public Requisicao findById(Long id){
        Optional<Requisicao> requisicao = this.requisicaoRepository.findById(id);
        return requisicao.orElseThrow(() -> new RuntimeException("Requisição não encontrada. Id "+ id + ", Tipo: " + Requisicao.class.getName()));
    }

    @Transactional
    public Requisicao create(Requisicao obj) {
        obj.setIdRequisicao(null);
        obj = this.requisicaoRepository.save(obj);
        return obj; 
    }


    public void delete(Long id) {
        findById(id);
        try {
            this.requisicaoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, já que há entidades relacionadas.");
        }
    }

    public void update(@Valid Requisicao obj) {
    	Requisicao existingRequisicao = findById(obj.getIdRequisicao());
    	existingRequisicao.setCliente(obj.getCliente());
    	existingRequisicao.setEntradaCliente(obj.getEntradaCliente());
    	existingRequisicao.setSaidaCliente(obj.getSaidaCliente());
    	existingRequisicao.setQtdPessoas(obj.getQtdPessoas());
    	existingRequisicao.setMesa(obj.getMesa());
    	
        this.requisicaoRepository.save(existingRequisicao);
    }

}


    
