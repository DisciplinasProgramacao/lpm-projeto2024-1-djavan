package djavan.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import djavan.demo.models.Mesa;
import djavan.demo.repositories.MesaRepository;
import jakarta.transaction.Transactional;

public class MesaService {
    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> getAll() {
        return mesaRepository.findAll();
    }

    public Mesa findById(int id){
        Optional<Mesa> user = Optional.ofNullable(this.mesaRepository.findById(id));
        return user.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado! Id: " + id + ", Tipo: " + Mesa.class.getName()
        ));
    }

    @Transactional
    public Mesa create(Mesa obj){ 
        obj.setId(0);
        obj = this.mesaRepository.save(obj);
        return obj;
    } 
}
