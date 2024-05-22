package djavan.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import djavan.demo.models.Requisicao;
import djavan.demo.repositories.RequisicaoRepository;
import jakarta.transaction.Transactional;

public class MesaService {
        @Autowired
    private RequisicaoRepository requisicaoRepository;

    public List<Requisicao> getAll() {
        return requisicaoRepository.findAll();
    }

    public Requisicao findById(Long id){
        Optional<Requisicao> user = this.requisicaoRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado! Id: " + id + ", Tipo: " + Requisicao.class.getName()
        ));
    }

    @Transactional
    public Requisicao create(Requisicao obj){ 
        //obj.setId(null);
        obj = this.requisicaoRepository.save(obj);
        return obj;
    } 
}
