package com.smartstake.api.services;

import com.smartstake.api.dto.OrderDTO;
import com.smartstake.api.exceptions.MissingOrderPortfolio;
import com.smartstake.api.model.repositories.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static java.util.Objects.isNull;

@Service
public class OrderService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PortfolioRepository portfolioRepository;

    private final String ORDER_ENDPOINT = "http://localhost:8080/stubs/order";

    public void createOrder(OrderDTO orderDTO) {
        if (isNull(orderDTO.getPortfolio()))
            throw new MissingOrderPortfolio("Missing portfolio in create order request");
        restTemplate.postForEntity(ORDER_ENDPOINT + "/create", orderDTO, String.class);
    }

    public void updateOrder(OrderDTO orderDTO) {
        restTemplate.postForEntity(ORDER_ENDPOINT + "/update", orderDTO, String.class);
    }

    public ResponseEntity<Boolean> deleteOrder(String id) {
        return restTemplate.execute( ORDER_ENDPOINT + "/delete", HttpMethod.DELETE, null, restTemplate.responseEntityExtractor(Boolean.class));
    }
}
