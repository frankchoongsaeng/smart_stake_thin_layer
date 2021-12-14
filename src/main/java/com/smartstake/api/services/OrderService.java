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

    private final String ORDER_ENDPOINT = "https://smartstakeorderservice.herokuapp.com";

    public ResponseEntity<String> createOrder(OrderDTO orderDTO) {
        OrderRequest orderRequest = OrderRequest.fromOrderDTO(orderDTO);
        return restTemplate.postForEntity(ORDER_ENDPOINT + "/create", orderRequest, String.class);
    }

    public ResponseEntity<String> updateOrder(OrderDTO orderDTO) {
        OrderRequest orderRequest = OrderRequest.fromOrderDTO(orderDTO);
        return restTemplate.postForEntity(ORDER_ENDPOINT + "/update", orderRequest, String.class);
    }

    public ResponseEntity<Boolean> deleteOrder(String id) {
        return restTemplate.execute( ORDER_ENDPOINT + "/cancel", HttpMethod.DELETE, null, restTemplate.responseEntityExtractor(Boolean.class));
    }


}

class OrderRequest {
    private String id;
    private String product;
    private int quantity;
    private double price;
    private Side side;
    private Long portfolioId;

    public static OrderRequest fromOrderDTO(OrderDTO orderDTO) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setId(orderRequest.getId());
        orderRequest.setPortfolioId(orderDTO.getPortfolio().getId());
        orderRequest.setPrice(orderDTO.getPrice());
        orderRequest.setProduct(orderDTO.getProduct());
        orderRequest.setQuantity(orderDTO.getQuantity());
        orderRequest.setSide(orderDTO.getSide());
        return orderRequest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }
}
