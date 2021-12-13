package com.smartstake.api.controllers;

import com.smartstake.api.dto.PortfolioDTO;
import com.smartstake.api.dto.PositionDTO;
import com.smartstake.api.exceptions.PortfolioNotFoundException;
import com.smartstake.api.model.entities.PortfolioEntity;
import com.smartstake.api.model.entities.PositionEntity;
import com.smartstake.api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/{clientId}")
    public Set<PortfolioEntity> getAllClientPortfolio(@PathVariable long clientId) {
        return clientService.getAllClientPortfolios(clientId);
    }


    @GetMapping("/{clientId}/portfolio/{portfolioId}")
    public PortfolioEntity getClientSinglePortfolio(@PathVariable long clientId, @PathVariable long portfolioId) {
        Optional<PortfolioEntity> optionalPortfolio = clientService.getSinglePortfolio(portfolioId);
        if(!optionalPortfolio.isPresent()) throw new PortfolioNotFoundException("portfolio with id: " + portfolioId + " was not found");
        return optionalPortfolio.get();
    }

    @PostMapping("/{clientId}/portfolio")
    public ResponseEntity<PortfolioEntity> createPortfolio(@RequestBody PortfolioDTO portfolio) {
           return clientService.createPortfolio(portfolio);
    }

    @PutMapping("/{clientId}/portfolio")
    public void updatePortfolio(@RequestBody PortfolioDTO portfolioDTO) {
        clientService.updatePortfolio(portfolioDTO);
    }

    @PutMapping("/{clientId}/position")
    public ResponseEntity<PositionEntity> createPosition(@RequestBody PositionDTO positionDTO) {
        return clientService.updatePosition(positionDTO);
    }
}
