package com.smartstake.api.dto;


import java.util.Date;

public class AuthResponseDTO {
    private String auth_token;
    private Date expires = new Date();

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
}