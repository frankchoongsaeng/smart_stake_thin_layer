package com.smartstake.api.stubs;

import com.smartstake.api.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Calendar;

@RestController
@RequestMapping("/stubs/auth")
public class AuthServiceStub {
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> registerStub(@RequestBody RegisterDTO registerDTO) {
        RegisterResponseUserDTO clientDTO = new RegisterResponseUserDTO();
        clientDTO.setEmail(registerDTO.getEmail());
        clientDTO.setFirstName(registerDTO.getFirstName());
        clientDTO.setLastName(registerDTO.getLastName());
        Random randomizer = new Random();
        clientDTO.setId(randomizer.nextLong(100l, 500l));

        AuthResponseDTO authResponse = new AuthResponseDTO();
        authResponse.setAuth_token("stubbed token");
        Calendar c = new GregorianCalendar(2021, Calendar.FEBRUARY, 11);
        Date d = c.getTime();
        authResponse.setExpires(d);

        RegisterResponseDTO responseDTO = new RegisterResponseDTO();
        responseDTO.setAuth(authResponse);
        responseDTO.setUser(clientDTO);
        return new ResponseEntity<RegisterResponseDTO>(responseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterResponseDTO> loginStub(@RequestBody LoginDTO loginDTO) {
        RegisterResponseUserDTO clientDTO = new RegisterResponseUserDTO();
        clientDTO.setUsername(loginDTO.getUsername());
        Random randomizer = new Random();
        clientDTO.setId(randomizer.nextLong(100l, 500l));

        AuthResponseDTO authResponse = new AuthResponseDTO();
        authResponse.setAuth_token("stubbed token");
        Calendar c = new GregorianCalendar(2021, Calendar.FEBRUARY, 11);
        Date d = c.getTime();
        authResponse.setExpires(d);

        RegisterResponseDTO responseDTO = new RegisterResponseDTO();
        responseDTO.setAuth(authResponse);
        responseDTO.setUser(clientDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}


