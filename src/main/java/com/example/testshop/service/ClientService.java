package com.example.testshop.service;

import com.example.testshop.dto.ClientDTO;
import com.example.testshop.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<ClientDTO> getAllClients();
    Optional<Client> getClientById(Long id);
    Client saveClient(Client client);
    Client updateClient(Client client);
    void deleteClientById(Long id);
    List<Client> getAllClientsByAddress(String address);
}
