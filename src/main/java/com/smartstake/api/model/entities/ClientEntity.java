package com.smartstake.api.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    private Long id;
    private String email;
    private String username;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    Set<PortfolioEntity> portfolio = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<PortfolioEntity> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Set<PortfolioEntity> portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
