package com.smartstake.api.model.repositories;

import com.smartstake.api.model.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
}
