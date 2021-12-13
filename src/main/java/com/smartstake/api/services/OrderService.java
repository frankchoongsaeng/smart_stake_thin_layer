package com.smartstake.api.services;

import com.smartstake.api.dto.OrderDTO;
import com.smartstake.api.enums.Side;
import com.smartstake.api.model.repositories.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PortfolioRepository portfolioRepository;

    private final int BUY_LIMIT = 10000;
    private final int SELL_LIMIT = 5000;
    private final double MAX_PRICE_SHIFT = 1.0;
    private final double LAST_TRADED_PRICE = 1.30;

    private final String ORDER_ENDPOINT = "https://smartstakeorderservice.herokuapp.com";

    public ResponseEntity<String> createOrder(OrderDTO orderDTO) {
        return restTemplate.postForEntity(ORDER_ENDPOINT + "/create", orderDTO, String.class);
    }

    public ResponseEntity<String> updateOrder(OrderDTO orderDTO) {
        return restTemplate.postForEntity(ORDER_ENDPOINT + "/update", orderDTO, String.class);
    }

    public ResponseEntity<Boolean> deleteOrder(String id) {
        return restTemplate.execute( ORDER_ENDPOINT + "/delete", HttpMethod.DELETE, null, restTemplate.responseEntityExtractor(Boolean.class));
    }

    public Boolean validateCreateOrder(OrderDTO orderDTO) {
        boolean isValid = false;
        // check that the bid price is within range
        isValid = isWithin(orderDTO.getPrice(),
                LAST_TRADED_PRICE + MAX_PRICE_SHIFT,
                LAST_TRADED_PRICE - MAX_PRICE_SHIFT);

        // validation for buy-side orders
        if (orderDTO.getSide().equals(Side.BUY)) {
            isValid = isWithin(orderDTO.getQuantity(), BUY_LIMIT, 1);
            isValid = orderDTO.getPortfolio().getBalance() >= (orderDTO.getPrice() * orderDTO.getQuantity());
        }
        // validation for sell-side orders
        else {
            isValid = isWithin(orderDTO.getQuantity(), SELL_LIMIT, 1);
            isValid = orderDTO
                    .getPortfolio()
                    .getPositions()
                    .stream()
                    .filter(positionDTO -> positionDTO.getProduct().equals(orderDTO.getProduct()))
                    .toList()
                    .get(0)
                    .getQuantity() >= orderDTO.getQuantity();
        }
        return isValid;
    }

    public Boolean validateUpdateOrder(OrderDTO orderDTO) {
        // implement update order logic
        return true;
    }

    private boolean isWithin(double price, double upperBound, double lowerBound) {
        return price <= upperBound && price >= lowerBound;
    }

    private boolean isWithin(int price, int upperBound, int lowerBound) {
        return price <= upperBound && price >= lowerBound;
    }
}
