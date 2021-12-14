package com.smartstake.api.model.repositories;

import com.smartstake.api.model.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
