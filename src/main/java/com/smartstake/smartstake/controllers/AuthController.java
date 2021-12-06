package com.smartstake.smartstake.controllers;

import com.smartstake.smartstake.dto.ClientDTO;
import com.smartstake.smartstake.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullFields;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/auth-service-stub")
    public ResponseEntity<ClientDTO> registerStub(@RequestBody RegisterDTO registerDTO) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setBalance(1000.0);
        clientDTO.setEmail(registerDTO.getEmail());
        clientDTO.setFirstname(registerDTO.getFirstname());
        clientDTO.setLastname(registerDTO.getLastname());
        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<ClientDTO> Register(@RequestBody RegisterDTO registerRequest) {
        if(registerRequest.getEmail() == null ||
            registerRequest.getFirstname() == null ||
            registerRequest.getLastname() == null ||
            registerRequest.getPassword() == null) return new ResponseEntity<ClientDTO>(HttpStatus.BAD_REQUEST);


        return restTemplate.postForEntity("http://localhost:8080/api/auth/auth-service-stub", registerRequest, ClientDTO.class);
    }
}
