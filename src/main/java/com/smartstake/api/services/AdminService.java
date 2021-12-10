package com.smartstake.api.services;

import com.smartstake.api.model.entities.ClientEntity;
import com.smartstake.api.model.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdminService {
    @Autowired
    ClientRepository clientRepository;

    public Set<ClientEntity> getAllClients() {
        Set<ClientEntity> clientEntities = new HashSet<>();
        clientRepository
                .findAll()
                .forEach(
                        clientEntity -> clientEntities.add(clientEntity)
                );
        return clientEntities;
    }
}
