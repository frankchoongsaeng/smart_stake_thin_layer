package com.smartstake.api.dto;

import com.smartstake.api.enums.Side;
import com.smartstake.api.enums.Status;

public class OrderDTO {
    private String id;
    private double price;
    private String product;
    private Side side;
    private Status status;
    private int quantity;
    private PortfolioDTO portfolio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PortfolioDTO getPortfolio() {
        return portfolio;
    }

    public void setPortfolioId(PortfolioDTO portfolio) {
        this.portfolio = portfolio;
    }
}
