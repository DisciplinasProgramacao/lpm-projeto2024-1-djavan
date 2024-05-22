package djavan.demo.controllers;

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
@RequestMapping("/Requisicao") //rota base desta classe é o requisicao
@Validated
public class RequisicaoController {
    @Autowired
    private RequisicaoService requisicaoService; 

    @GetMapping("/{id}") //entre chaves delimita uma variável
    public ResponseEntity<Requisicao> findById(@PathVariable Long id) { //entidade de resposta do tipo REQUISICAO
        Requisicao obj = this.requisicaoService.findById(id);
        return ResponseEntity.ok().body(obj); 
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Requisicao obj) {
        this.requisicaoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdRequisicao()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Requisicao obj, @PathVariable Long id){
        obj.setIdRequisicao(id);
        this.requisicaoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.requisicaoService.delete(id); 
        return ResponseEntity.noContent().build();
    }

}
