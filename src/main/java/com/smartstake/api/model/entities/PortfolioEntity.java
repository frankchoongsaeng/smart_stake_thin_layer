package com.smartstake.api.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "portfolio")
public class PortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public PortfolioEntity() {}

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

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
