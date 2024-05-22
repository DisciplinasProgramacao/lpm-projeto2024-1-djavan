package djavan.demo.controllers;

import djavan.demo.models.Cliente;
import djavan.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClienteService clientService;

    @GetMapping
    public List<Cliente> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Cliente getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public Cliente createClient(@RequestBody Cliente cliente) {
        return clientService.createClient(cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
