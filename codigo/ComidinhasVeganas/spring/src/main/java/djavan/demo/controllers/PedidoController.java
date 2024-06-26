package djavan.demo.controllers;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import djavan.demo.models.Item;
import djavan.demo.models.Pedido;
import djavan.demo.service.PedidoService;
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

    @GetMapping("/user/{clienteId}")
    public ResponseEntity<List<Pedido>> findAllByClienteId(@PathVariable Long clienteId) {
        List<Pedido> objs = this.pedidoService.findAllByClienteId(clienteId);
        return ResponseEntity.ok().body(objs);
    }

    @PostMapping("/{id}/produto")
    public ResponseEntity<Pedido> adicionarProduto(@PathVariable Long id, @RequestBody Item produto) {
        try {
            Pedido pedido = this.pedidoService.addPedidos(id, produto);
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
