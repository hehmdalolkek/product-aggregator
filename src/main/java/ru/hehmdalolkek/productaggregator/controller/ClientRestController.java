package ru.hehmdalolkek.productaggregator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.hehmdalolkek.productaggregator.model.Client;
import ru.hehmdalolkek.productaggregator.service.ClientService;

import java.util.List;

/**
 * Rest controller for <code>Client</code>.
 *
 * @author Inna Badekha
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clients")
public class ClientRestController {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Client createClient(@RequestBody Client client) {
        return this.clientService.createClient(client);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Client> getAllClients() {
        return this.clientService.getAllClients();
    }

    @GetMapping("/{clientId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Client getClientById(@PathVariable("clientId") Long clientId) {
        return this.clientService.getClientById(clientId);
    }

    @DeleteMapping("/{clientId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteClientById(@PathVariable("clientId") Long clientId) {
        this.clientService.deleteClientById(clientId);
    }

}
