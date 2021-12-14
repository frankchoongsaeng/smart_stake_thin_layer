package com.smartstake.api.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "portfolio")
public class PortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double balance;

    public PortfolioEntity() {}

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<PositionEntity> positions = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<OrderEntity> orders = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity clientEntity) {
        this.client = clientEntity;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Set<PositionEntity> getPositions() {
        return positions;
    }

    public void setPositions(Set<PositionEntity> positions) {
        this.positions = positions;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "PortfolioEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", client=" + client +
                '}';
    }
}
