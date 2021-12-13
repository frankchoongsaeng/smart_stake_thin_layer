package com.smartstake.api.dto;

import java.util.Date;


public class RegisterResponseDTO {

    private RegisterResponseUserDTO user = new RegisterResponseUserDTO();
    private AuthResponseDTO auth = new AuthResponseDTO();

    public RegisterResponseUserDTO getUser() {
        return user;
    }

    public void setUser(RegisterResponseUserDTO user) {
        this.user = user;
    }

    public AuthResponseDTO getAuth() {
        return auth;
    }

    public void setAuth(AuthResponseDTO auth) {
        this.auth = auth;
    }
}
