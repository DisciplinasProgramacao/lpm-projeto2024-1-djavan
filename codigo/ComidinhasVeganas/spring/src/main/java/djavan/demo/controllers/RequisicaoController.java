package djavan.demo.controllers;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import djavan.demo.models.Mesa;
import djavan.demo.models.Requisicao;
import djavan.demo.service.RequisicaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/requisicao")
@Validated //Tem que validar qualquer tipo de validação tipo @NotNull etc. colocada no Model

public class RequisicaoController {

    @Autowired
    private RequisicaoService requisicaoService;
    
    /**
     * Endpoint para buscar uma requisição pelo seu ID.
     *
     * @param id O ID da requisição a ser buscada.
     * @return Um ResponseEntity contendo o objeto Requisicao encontrado e um status 200 (OK).
     */
    @GetMapping("/{id}") // Se eu quero buscar alguma coisa no HTTP, eu preciso fazer um GET. Delimito entre chaves porque é uma variável.
    public ResponseEntity<Requisicao> findById(@PathVariable Long id) { // Quando você vai retornar um dado pro front-end, é preciso retornar uma entidade.
        Requisicao obj = this.requisicaoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    /**
     * Endpoint para criar uma nova requisição.
     *
     * @param obj O objeto Requisicao a ser criado.
     * @return Um ResponseEntity com status 201 (Created) e o URI do novo recurso criado.
     */
    @PostMapping // Criar um dado no HTTP
    @Validated
    public ResponseEntity<Void> create(@RequestBody Requisicao obj) {
        this.requisicaoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdRequisicao()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    /**
     * Endpoint para encerrar uma requisição.
     *
     * @param id O ID da requisição a ser encerrada.
     * @param mesaOcupada O objeto Mesa representando a mesa ocupada associada à requisição.
     * @return Um ResponseEntity com status 204 (No Content) se a operação for bem-sucedida, ou 404 (Not Found) se a requisição não for encontrada.
     */
    @PutMapping("/{id}/encerrar")
    public ResponseEntity<Void> encerrar(@PathVariable Long id, @Valid @RequestBody Mesa mesaOcupada) {
        Requisicao requisicaoAEncerrar = requisicaoService.findById(id);
        if (requisicaoAEncerrar == null) {
            return ResponseEntity.notFound().build();
        }
        requisicaoService.encerrar(requisicaoAEncerrar, mesaOcupada);
        return ResponseEntity.noContent().build();
    }
}