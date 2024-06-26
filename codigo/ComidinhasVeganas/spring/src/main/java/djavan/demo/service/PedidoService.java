package djavan.demo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import djavan.demo.models.Cliente;
import djavan.demo.models.Item;
import djavan.demo.models.Pedido;
import djavan.demo.repositories.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    public Pedido findById(Long id) {
        Optional<Pedido> pedido = this.pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
    }

    public List<Pedido> findAllByClienteId(Long clienteId) {
        List<Pedido> pedidos = this.pedidoRepository.findByCliente_Id(clienteId);
        return pedidos;
    }

    @Transactional
    public Pedido create(Pedido obj) {
        Cliente cliente = this.clienteService.findById(obj.getCliente().getId()); //validação que o usuário existe
        obj.setCliente(cliente);
        obj = this.pedidoRepository.save(obj);
        return obj;
    }

    @Transactional
    public Pedido update(Pedido obj) {
        Pedido newObj = findById(obj.getId());

        return this.pedidoRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);

        try {
            this.pedidoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir.");
        }
    }

    public List<Pedido> findAllPedidos() {
        return this.pedidoRepository.findAll();
    }

    public Pedido addPedidos(Long id, Item produto) {
        Pedido pedido = findById(id);
        if (pedido != null) {
            pedido.adicionarItem(produto);
             return pedidoRepository.save(pedido);
         }
         return null;
    }

    // Método deve ser atualizado! Agora é Item.
    // public Pedido adicionarProduto(Long id, Produto produto) {
    //     Pedido pedido = findById(id);
    //     if (pedido != null) {
    //         pedido.adicionarProduto(produto);
    //         return pedidoRepository.save(pedido);
    //     }
    //     return null;
    // }

}

