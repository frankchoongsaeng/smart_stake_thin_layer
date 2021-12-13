package com.smartstake.api.controllers;

import com.smartstake.api.dto.ClientDTO;
import com.smartstake.api.dto.LoginDTO;
import com.smartstake.api.dto.RegisterDTO;
import com.smartstake.api.dto.RegisterResponseDTO;
import com.smartstake.api.services.AuthService;
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
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO registerRequest) {
        /*
        * register and log in the user in one request
        * */
        ResponseEntity<RegisterResponseDTO> registerResponse = authService.register(registerRequest);

        if(!registerResponse.getStatusCode().is2xxSuccessful()) return registerResponse;
        LoginDTO loginData = new LoginDTO();
        loginData.setUsername(registerRequest.getUsername());
        loginData.setPassword(registerRequest.getPassword());
        return authService.login(loginData);
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }
}
