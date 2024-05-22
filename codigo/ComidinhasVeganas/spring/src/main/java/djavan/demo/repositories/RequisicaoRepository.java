package djavan.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import djavan.demo.models.Requisicao;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
    //Requisicao findById(String username);
    
}
