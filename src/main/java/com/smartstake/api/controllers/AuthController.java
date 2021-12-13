package com.smartstake.api.controllers;

import com.smartstake.api.dto.*;
import com.smartstake.api.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthServiceLoginResponseDTO> register(@RequestBody RegisterDTO registerRequest) {
        /*
        * register and log in the user in one request
        * */
        ResponseEntity<AuthServiceLoginResponseDTO> registerResponse = authService.register(registerRequest);

        return new ResponseEntity<>(registerResponse.getBody(), HttpStatus.OK);
    }


    //@PostMapping("/login")
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthServiceLoginResponseDTO> login(LoginDTO loginDTO) {

        return authService.login(loginDTO);
    }
}
