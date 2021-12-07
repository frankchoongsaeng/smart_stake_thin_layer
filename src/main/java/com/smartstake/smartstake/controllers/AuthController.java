package com.smartstake.smartstake.controllers;

import com.smartstake.smartstake.dto.ClientDTO;
import com.smartstake.smartstake.dto.LoginDTO;
import com.smartstake.smartstake.dto.RegisterDTO;
import com.smartstake.smartstake.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ClientDTO> register(@RequestBody RegisterDTO registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<ClientDTO> login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }
}
