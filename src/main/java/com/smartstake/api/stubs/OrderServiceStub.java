package com.smartstake.api.stubs;

import com.smartstake.api.dto.ClientDTO;
import com.smartstake.api.dto.LoginDTO;
import com.smartstake.api.dto.RegisterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stubs/order")
public class OrderServiceStub {
    @PostMapping("/create")
    public ResponseEntity<ClientDTO> registerStub(@RequestBody RegisterDTO registerDTO) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setEmail(registerDTO.getEmail());
        clientDTO.setFirstName(registerDTO.getFirstName());
        clientDTO.setLastName(registerDTO.getLastName());
        clientDTO.setId(100L);
        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<ClientDTO> loginStub(@RequestBody LoginDTO loginDTO) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setEmail(loginDTO.getEmail());
        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.CREATED);
    }
}
