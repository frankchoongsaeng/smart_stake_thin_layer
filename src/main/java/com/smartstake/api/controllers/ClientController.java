package com.smartstake.api.controllers;

import com.smartstake.api.dto.PortfolioDTO;
import com.smartstake.api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/portfolio/create")
    public void createPortfolio(@RequestBody PortfolioDTO portfolio) {
           clientService.createPortfolio(portfolio);
    }
}
