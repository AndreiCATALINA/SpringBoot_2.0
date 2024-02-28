package com.example.testshop.controller;

import com.example.testshop.dto.ClientDTO;
import com.example.testshop.exception.ResourceNotFoundException;
import com.example.testshop.model.Client;
import com.example.testshop.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping //http://localhost:8181/api/client
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            throw new ResourceNotFoundException("There are no clients in DB");
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/clientById/{id}") //http://localhost:8181/api/client/clientById/{id}
    public ResponseEntity<Optional<ClientDTO>> getClientById(@PathVariable Long id) {
        Optional<ClientDTO> clientOptional = clientService.getClientById(id);
        clientOptional.orElseThrow(() ->
                new ResourceNotFoundException("The client with id: " + id + " doesn't exist in DB"));
        return new ResponseEntity<>(clientOptional, HttpStatus.OK);
    }

    @GetMapping("/clientsByAddress/{address}") //http://localhost:8181/api/client/clientsByAddress/{address}
    public ResponseEntity<List<Client>> getAllClientsByAddress(@PathVariable String address) {
        List<Client> addressList = clientService.getAllClientsByAddress(address);
        if (addressList.isEmpty()) {
            throw new ResourceNotFoundException("The client with address: " + address + " doesn't exist in DB");
        }
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @PostMapping("/saveNewClient") //http://localhost:8181/api/client/saveNewClient
    public ResponseEntity<Client> saveClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.saveClient(client), HttpStatus.OK);
    }

    @PutMapping("/updateClient") //http://localhost:8181/api/client/updateClient
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.updateClient(client), HttpStatus.OK);
    }

    @DeleteMapping("/deleteClientById/{id}") //http://localhost:8181/api/client/deleteClientById/{id}
    public ResponseEntity<?> deleteClientById(@PathVariable Long id) {
        Optional<ClientDTO> clientOptional = clientService.getClientById(id);
        clientOptional.orElseThrow(() ->
                new ResourceNotFoundException("The client with id: " + id + " doesn't exist in DB"));
        clientService.deleteClientById(id);
        return new ResponseEntity<>("The client with id " + id + " was deleted", HttpStatus.OK);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
