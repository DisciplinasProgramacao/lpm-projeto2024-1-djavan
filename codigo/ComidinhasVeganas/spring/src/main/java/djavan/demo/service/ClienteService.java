// ClientService.java
package djavan.demo.service;

import djavan.demo.models.Cliente;
import djavan.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClients() {
        return clienteRepository.findAll();
    }

    public Cliente getClientById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente createClient(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteClient(Long id) {
        clienteRepository.deleteById(id);
    }
}