package djavan.demo.service;

import djavan.demo.models.Pedido;
import djavan.demo.models.Cliente;
import djavan.demo.repositories.PedidoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    
    public Pedido atualizarStatus(Long id, String novoStatus) {
        return null;
    }

    public List<Pedido> findAllPedidos() {
        return this.pedidoRepository.findAll();
    }

}

