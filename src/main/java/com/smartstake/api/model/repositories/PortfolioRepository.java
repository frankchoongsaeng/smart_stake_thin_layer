package com.smartstake.api.model.repositories;

import com.smartstake.api.model.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {
    public Set<PortfolioEntity> findAllByClientId(long clientId);
}