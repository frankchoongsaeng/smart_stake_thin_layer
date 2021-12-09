package com.smartstake.api.controllers;

import com.smartstake.api.dto.PortfolioDTO;
import com.smartstake.api.model.entities.PortfolioEntity;
import com.smartstake.api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/portfolio/create")
    public ResponseEntity<PortfolioEntity> createPortfolio(@RequestBody PortfolioDTO portfolio) {
           return clientService.createPortfolio(portfolio);
    }

    @PutMapping("/portfolio/update")
    public void updatePortfolio(@RequestBody PortfolioDTO portfolioDTO) {
        clientService.updatePortfolio(portfolioDTO);
    }
}
