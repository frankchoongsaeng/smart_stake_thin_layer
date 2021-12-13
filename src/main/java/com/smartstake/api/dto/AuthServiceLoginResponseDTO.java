package com.smartstake.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthServiceLoginResponseDTO {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expiry_date")
    private String expiryDate;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("client_id")
    private Integer clientId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = Integer.parseInt(clientId);
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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
}
