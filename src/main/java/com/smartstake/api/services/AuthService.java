package com.smartstake.api.services;

import com.smartstake.api.dto.ClientDTO;
import com.smartstake.api.dto.LoginDTO;
import com.smartstake.api.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.util.Objects.isNull;

@Service
public class AuthService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ClientService clientService;

    private final String AUTH_ENDPOINT = "http://localhost:8080/stubs/auth";

    public ResponseEntity<ClientDTO> login(LoginDTO loginDTO) {
        if (loginDTO.getEmail() == null || loginDTO.getPassword() == null)
            return new ResponseEntity<ClientDTO>(HttpStatus.BAD_REQUEST);

        return restTemplate.postForEntity(AUTH_ENDPOINT + "/login", loginDTO, ClientDTO.class);
    }

    public ResponseEntity<ClientDTO> register(RegisterDTO registerRequest) {
        if (isNull(registerRequest.getEmail()) ||
                isNull(registerRequest.getFirstName()) ||
                isNull(registerRequest.getLastName()) ||
                isNull(registerRequest.getPassword()))
            return new ResponseEntity<ClientDTO>(HttpStatus.BAD_REQUEST);

        ResponseEntity<ClientDTO> newClient = restTemplate.postForEntity(AUTH_ENDPOINT + "/register", registerRequest, ClientDTO.class);

        if(newClient.getStatusCodeValue() == 200 || newClient.getStatusCodeValue() == 201)
            clientService.saveClient(newClient.getBody());

        return newClient;
    }
}
