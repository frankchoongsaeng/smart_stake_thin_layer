package com.smartstake.smartstake.services;

import com.smartstake.smartstake.dto.ClientDTO;
import com.smartstake.smartstake.dto.LoginDTO;
import com.smartstake.smartstake.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthService {
    @Autowired
    static RestTemplate restTemplate;

    public static ResponseEntity<ClientDTO> login(LoginDTO loginDTO) {
        if (loginDTO.getEmail() == null || loginDTO.getPassword() == null)
            return new ResponseEntity<ClientDTO>(HttpStatus.BAD_REQUEST);

        return restTemplate.postForEntity("http://localhost:8080/auth-service-stub/login", loginDTO, ClientDTO.class);
    }

    public static ResponseEntity<ClientDTO> register(RegisterDTO registerRequest) {
        if (registerRequest.getEmail() == null ||
                registerRequest.getFirstname() == null ||
                registerRequest.getLastname() == null ||
                registerRequest.getPassword() == null)
            return new ResponseEntity<ClientDTO>(HttpStatus.BAD_REQUEST);

        return restTemplate.postForEntity("http://localhost:8080/auth-service-stub/register", registerRequest, ClientDTO.class);
    }
}
