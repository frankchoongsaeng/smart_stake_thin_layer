package com.smartstake.api.services;

import com.smartstake.api.dto.PortfolioDTO;
import com.smartstake.api.model.entities.PortfolioEntity;
import com.smartstake.api.model.repositories.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    PortfolioRepository portfolioRepository;

    public void createPortfolio(PortfolioDTO portfolio) {

    }
}
