import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import djavan.demo.models.Requisicao;
import djavan.demo.service.RequisicaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/requisicao")
@Validated //Tem que validar qualquer tipo de validação tipo @NotNull etc. colocada no Model

public class RequisicaoController {

    @Autowired
    private RequisicaoService requisicaoService;
    
    @GetMapping("/{id}") // Se eu quero buscar alguma coisa no HTTP, eu preciso fazer um GET. Delimito entre chaves porque é uma variável.
    public ResponseEntity<Requisicao> findById(@PathVariable Long id) { // Quando você vai retornar um dado pro front-end, é preciso retornar uma entidade.
        Requisicao obj = this.requisicaoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping // Criar um dado no HTTP
    @Validated
    public ResponseEntity<Void> create(@RequestBody Requisicao obj) {
        this.requisicaoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
}
