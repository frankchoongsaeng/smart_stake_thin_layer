package com.smartstake.api.services;

import com.smartstake.api.dto.ClientDTO;
import com.smartstake.api.dto.LoginDTO;
import com.smartstake.api.dto.RegisterDTO;
import com.smartstake.api.dto.RegisterResponseDTO;
import com.smartstake.api.model.entities.ClientEntity;
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

    public ResponseEntity<RegisterResponseDTO> login(LoginDTO loginDTO) {
        if (isNull(loginDTO.getUsername()) || isNull(loginDTO.getPassword())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return restTemplate.postForEntity(AUTH_ENDPOINT + "/login", loginDTO, RegisterResponseDTO.class);
    }

    public ResponseEntity<RegisterResponseDTO> register(RegisterDTO registerRequest) {
        if (isNull(registerRequest.getEmail()) ||
                isNull(registerRequest.getFirstName()) ||
                isNull(registerRequest.getLastName()) ||
                isNull(registerRequest.getPassword()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<RegisterResponseDTO> newClient = restTemplate.postForEntity(AUTH_ENDPOINT + "/register", registerRequest, RegisterResponseDTO.class);

        if(newClient.getStatusCode().is2xxSuccessful()) {
            RegisterResponseDTO client = newClient.getBody();
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setId(client.getUser().getId());
            clientDTO.setEmail(client.getUser().getEmail());
            clientService.saveClient(clientDTO);
        }

        return newClient;
    }
}
