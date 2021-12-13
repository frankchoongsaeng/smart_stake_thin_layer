package com.smartstake.api.services;

import com.smartstake.api.dto.ClientDTO;
import com.smartstake.api.dto.PortfolioDTO;
import com.smartstake.api.dto.PositionDTO;
import com.smartstake.api.exceptions.ClientNotFoundException;
import com.smartstake.api.exceptions.PortfolioNotFoundException;
import com.smartstake.api.model.entities.ClientEntity;
import com.smartstake.api.model.entities.PortfolioEntity;
import com.smartstake.api.model.entities.PositionEntity;
import com.smartstake.api.model.repositories.ClientRepository;
import com.smartstake.api.model.repositories.PortfolioRepository;
import com.smartstake.api.model.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
public class ClientService {

    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PositionRepository positionRepository;

    public void saveClient(ClientDTO body) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setEmail(body.getEmail());
        clientEntity.setId(body.getId());
        clientEntity.setUsername(body.getUsername());
        clientRepository.save(clientEntity);
    }

    public ResponseEntity<PortfolioEntity> createPortfolio(PortfolioDTO portfolio) {
        Optional<ClientEntity> clientEntity = clientRepository.findById(portfolio.getClientId());

        if(clientEntity.isEmpty()) throw new ClientNotFoundException(String.format("Could not find client with id: %s", portfolio.getClientId()));

        PortfolioEntity portfolioEntity = new PortfolioEntity();
        portfolioEntity.setDescription(portfolio.getDescription());
        portfolioEntity.setName(portfolio.getName());
        portfolioEntity.setClient(clientEntity.get());
        portfolioEntity.setBalance(portfolio.getBalance());
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

    public Set<PortfolioEntity> getAllClientPortfolios(long clientId) {
        return portfolioRepository.findAllByClientId(clientId);
    }

    public Optional<PortfolioEntity> getSinglePortfolio(long portfolioId) {
        return portfolioRepository.findById(portfolioId);
    }

    public ResponseEntity<PositionEntity> updatePosition(PositionDTO positionDTO) {
        Optional<PortfolioEntity> portfolioEntity = portfolioRepository.findById(positionDTO.getPortfolioId());

        if(portfolioEntity.isEmpty()) throw new PortfolioNotFoundException(String.format("Could not find portfolio with id: %s", positionDTO.getPortfolioId()));

        // fetch the position from db. update if exists, else create
        if(isNull(positionDTO.getId())) {
            Optional<PositionEntity> optionalPositionEntity = positionRepository.findById(positionDTO.getId());

            if(optionalPositionEntity.isEmpty()) {
                PositionEntity positionEntity = new PositionEntity();
                positionEntity.setProduct(positionDTO.getProduct());
                positionEntity.setQuantity(positionDTO.getQuantity());
                positionEntity.setPortfolio(portfolioEntity.get());
                positionRepository.save(positionEntity);
                return new ResponseEntity<>(positionEntity, HttpStatus.OK);
            }

            PositionEntity positionEntity = new PositionEntity();
            positionEntity.setProduct(positionDTO.getProduct());
            positionEntity.setQuantity(positionDTO.getQuantity());
            positionEntity.setPortfolio(portfolioEntity.get());
            positionRepository.save(positionEntity);
            return new ResponseEntity<>(positionEntity, HttpStatus.OK);

        } else {
            PositionEntity positionEntity = new PositionEntity();
            positionEntity.setProduct(positionDTO.getProduct());
            positionEntity.setQuantity(positionDTO.getQuantity());
            positionEntity.setPortfolio(portfolioEntity.get());
            positionRepository.save(positionEntity);
            return new ResponseEntity<>(positionEntity, HttpStatus.OK);
        }

    }
}
