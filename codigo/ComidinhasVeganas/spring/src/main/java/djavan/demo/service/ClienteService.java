// ClientService.java
package djavan.demo.service;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import djavan.demo.models.Cliente;
import djavan.demo.repositories.ClienteRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Long id){
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new RuntimeException("Cliente não encontrado. Id "+ id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente create(Cliente obj) {
        obj.setId(null);
        obj = this.clienteRepository.save(obj);
        return obj; 
    }


    public void delete(Long id) {
        findById(id);
        try {
            this.clienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, já que há entidades relacionadas.");
        }
    }

    public void update(@Valid Cliente obj) {
        Cliente existingCliente = findById(obj.getId());
        existingCliente.setNome(obj.getNome());

        this.clienteRepository.save(existingCliente);
    }

}


    
