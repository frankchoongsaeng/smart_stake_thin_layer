package com.smartstake.smartstake.stubs;

import com.smartstake.smartstake.dto.ClientDTO;
import com.smartstake.smartstake.dto.LoginDTO;
import com.smartstake.smartstake.dto.RegisterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth-service-stub")
public class AuthServiceStub {
    @PostMapping("/register")
    public ResponseEntity<ClientDTO> registerStub(@RequestBody RegisterDTO registerDTO) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setBalance(1000.0);
        clientDTO.setEmail(registerDTO.getEmail());
        clientDTO.setFirstname(registerDTO.getFirstname());
        clientDTO.setLastname(registerDTO.getLastname());
        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ClientDTO> loginStub(@RequestBody LoginDTO loginDTO) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setBalance(1000.0);
        clientDTO.setEmail(loginDTO.getEmail());
        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.CREATED);
    }
}
