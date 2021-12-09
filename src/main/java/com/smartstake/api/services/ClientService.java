package com.smartstake.api.services;

import com.smartstake.api.dto.ClientDTO;
import com.smartstake.api.dto.PortfolioDTO;
import com.smartstake.api.exceptions.ClientNotFoundException;
import com.smartstake.api.exceptions.PortfolioNotFoundException;
import com.smartstake.api.model.entities.ClientEntity;
import com.smartstake.api.model.entities.PortfolioEntity;
import com.smartstake.api.model.repositories.ClientRepository;
import com.smartstake.api.model.repositories.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ClientService {

    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    ClientRepository clientRepository;

    public void saveClient(ClientDTO body) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setEmail(body.getEmail());
        clientEntity.setId(body.getId());
        clientRepository.save(clientEntity);
    }

    public ResponseEntity<PortfolioEntity> createPortfolio(PortfolioDTO portfolio) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(portfolio.getClientId());

        if(clientEntity.isEmpty()) throw new ClientNotFoundException(String.format("Could not find client with id: %s", portfolio.getClientId()));

        PortfolioEntity portfolioEntity = new PortfolioEntity();
        portfolioEntity.setDescription(portfolio.getDescription());
        portfolioEntity.setName(portfolio.getName());
        portfolioEntity.setClient(clientEntity.get());
        portfolioRepository.save(portfolioEntity);
        return new ResponseEntity<>(portfolioEntity, HttpStatus.OK);
    }

    public void updatePortfolio(PortfolioDTO portfolioDTO) {
        Optional<PortfolioEntity> portfolioEntityOption = portfolioRepository.findById(portfolioDTO.getId());

        if(portfolioEntityOption.isEmpty()) throw new PortfolioNotFoundException(String.format("Could not find client with id: %s", portfolioDTO.getClientId()));

        PortfolioEntity portfolioEntity = portfolioEntityOption.get();
        if(!isNull(portfolioDTO.getDescription())) portfolioEntity.setDescription(portfolioDTO.getDescription());
        if(!isNull(portfolioDTO.getName())) portfolioEntity.setName(portfolioDTO.getName());

        portfolioRepository.save(portfolioEntity);
    }
}
