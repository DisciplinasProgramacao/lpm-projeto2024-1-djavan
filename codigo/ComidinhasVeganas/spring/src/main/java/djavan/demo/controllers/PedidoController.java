package djavan.demo.controllers;
import djavan.demo.models.Pedido;
import djavan.demo.service.PedidoService;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido")
@Validated

public class PedidoController {
    @Autowired
    private PedidoService pedidoService; 


    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        Pedido obj = this.pedidoService.findById(id); 
        return ResponseEntity.ok().body(obj); 
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findAllPedidos() {
        List<Pedido> pedidos = this.pedidoService.findAllPedidos();
        return ResponseEntity.ok().body(pedidos);
    }


    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Pedido obj) {
        this.pedidoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.pedidoService.delete(id); 
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Pedido>> findAllByClienteId(@PathVariable Long clienteId) {
        List<Pedido> objs = this.pedidoService.findAllByClienteId(clienteId);
        return ResponseEntity.ok().body(objs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarStatusPedido(@PathVariable("id") Long id, @RequestBody Map<String, String> requestBody) {
        String novoStatus = requestBody.get("status");
        try {
            Pedido pedidoAtualizado = pedidoService.atualizarStatus(id, novoStatus);
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar o status do agendamento.");
        }
    }

    
    }




    
