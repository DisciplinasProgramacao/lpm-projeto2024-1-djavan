package djavan.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import djavan.demo.models.Mesa;
import djavan.demo.service.MesaService;

public class MesaController {
    
    @Autowired
    private MesaService mesaService;
    
    @GetMapping("/{id}") // Se eu quero buscar alguma coisa no HTTP, eu preciso fazer um GET. Delimito entre chaves porque é uma variável.
    public ResponseEntity<Mesa> findById(@PathVariable Long id) { // Quando você vai retornar um dado pro front-end, é preciso retornar uma entidade.
        Mesa obj = this.mesaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping // Criar um dado no HTTP
    @Validated
    public ResponseEntity<Void> create(@RequestBody Mesa obj) {
        this.mesaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
