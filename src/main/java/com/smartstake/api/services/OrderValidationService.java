package com.smartstake.api.services;

import com.smartstake.api.dto.OrderDTO;
import com.smartstake.api.enums.Side;
import org.springframework.stereotype.Service;

@Service
public class OrderValidationService {

    private final int BUY_LIMIT = 10000;
    private final int SELL_LIMIT = 5000;
    private final double MAX_PRICE_SHIFT = 1.0;
    private final double LAST_TRADED_PRICE = 1.0;

    public Boolean validateCreateOrder(OrderDTO orderDTO) {
        // check that the bid price is within range
        if(!isWithin(orderDTO.getPrice(),
                LAST_TRADED_PRICE + MAX_PRICE_SHIFT,
                LAST_TRADED_PRICE - MAX_PRICE_SHIFT)) return false;

        // validation for buy-side orders
        if (orderDTO.getSide().equals(Side.BUY)) {
            if(!isWithin(orderDTO.getQuantity(), BUY_LIMIT, 1)) return false;
            if(!(orderDTO.getPortfolio().getBalance() >= (orderDTO.getPrice() * orderDTO.getQuantity()))) return false;
        }
        // validation for sell-side orders
        else {
            if(!isWithin(orderDTO.getQuantity(), SELL_LIMIT, 1)) return false;
            if(!(orderDTO
                    .getPortfolio()
                    .getPositions()
                    .stream()
                    .filter(positionDTO -> positionDTO.getProduct().equals(orderDTO.getProduct()))
                    .toList()
                    .get(0)
                    .getQuantity() >= orderDTO.getQuantity())) return false;
        }
        return true;
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
