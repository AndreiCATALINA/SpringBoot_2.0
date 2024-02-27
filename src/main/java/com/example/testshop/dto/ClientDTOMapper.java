package com.example.testshop.dto;

import com.example.testshop.model.Client;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ClientDTOMapper implements Function<Client,ClientDTO> {
    @Override
    public ClientDTO apply(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getEmail()
        );
    }
}
