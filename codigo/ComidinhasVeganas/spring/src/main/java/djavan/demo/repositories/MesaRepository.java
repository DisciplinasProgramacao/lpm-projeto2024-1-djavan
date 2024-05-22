package djavan.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import djavan.demo.models.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
    Mesa findById(int idMesa);

}
