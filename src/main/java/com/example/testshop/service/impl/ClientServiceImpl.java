package com.example.testshop.service.impl;

import com.example.testshop.model.Client;
import com.example.testshop.repository.ClientRepository;
import com.example.testshop.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClientById(Long id) {
      clientRepository.deleteById(id);
    }

    @Override
    public List<Client> getAllClientsByAddress(String address) {
        return clientRepository.getClientsByAddress(address);
    }
}
