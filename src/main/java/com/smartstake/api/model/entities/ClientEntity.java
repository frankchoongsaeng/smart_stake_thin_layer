package com.smartstake.api.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    private Long id;

    private String email;

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
}
