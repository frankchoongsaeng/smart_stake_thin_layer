package com.smartstake.api.dto;

import java.util.HashSet;
import java.util.Set;

public class ClientDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private long id;
    private Set<PortfolioDTO> portfolios = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<PortfolioDTO> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(Set<PortfolioDTO> portfolioDTOS) {
        this.portfolios = portfolioDTOS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
