package com.smartstake.api.services;

import com.smartstake.api.dto.*;
import com.smartstake.api.model.entities.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static java.util.Objects.isNull;


@Service
public class AuthService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ClientService clientService;

//    private final String AUTH_ENDPOINT = "http://localhost:8080/stubs/auth";
    private final String AUTH_ENDPOINT = "https://smartstakeauthservice.herokuapp.com";

    public ResponseEntity<AuthServiceLoginResponseDTO> login(LoginDTO loginDTO) {

        System.out.println(loginDTO);
        if (isNull(loginDTO.getUsername()) || isNull(loginDTO.getPassword())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("username", loginDTO.getUsername());
        form.add("password", loginDTO.getPassword());
        HttpEntity<MultiValueMap> entity = new HttpEntity(form, headers);
        System.out.println("login request about to be sent");
        return restTemplate.exchange(AUTH_ENDPOINT + "/login", HttpMethod.POST, entity, AuthServiceLoginResponseDTO.class);
    }

    public ResponseEntity<AuthServiceLoginResponseDTO> register(RegisterDTO registerRequest) {
        if (isNull(registerRequest.getEmail()) ||
                isNull(registerRequest.getFirstName()) ||
                isNull(registerRequest.getLastName()) ||
                isNull(registerRequest.getPassword()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

//        ResponseEntity<String> registerResponse = restTemplate.postForEntity(AUTH_ENDPOINT + "/registration", registerRequest, String.class);
        ResponseEntity<String> registerResponse;
        try {
            registerResponse = restTemplate.postForEntity(AUTH_ENDPOINT + "/registration", registerRequest, String.class);
        } catch (HttpClientErrorException clientErrorException) {
            return new ResponseEntity<>(clientErrorException.getStatusCode());
        } catch (HttpServerErrorException serverErrorException) {
            return new ResponseEntity<>(serverErrorException.getStatusCode());
        }

        // return the registration response if it was not successful
        if(!registerResponse.getStatusCode().is2xxSuccessful()) return new ResponseEntity<>(registerResponse.getStatusCode());

        // attempt to log in the user if the registration was successful
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(registerRequest.getUsername());
        loginDTO.setPassword(registerRequest.getPassword());
        ResponseEntity<AuthServiceLoginResponseDTO> authServiceLoginResponse = this.login(loginDTO);

        // return the log in response if the login failed
        if(!authServiceLoginResponse.getStatusCode().is2xxSuccessful()) return authServiceLoginResponse;


        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(authServiceLoginResponse.getBody().getClientId());
        clientDTO.setEmail(authServiceLoginResponse.getBody().getEmail());
        clientDTO.setUsername(authServiceLoginResponse.getBody().getUsername());
        clientService.saveClient(clientDTO);


        return authServiceLoginResponse;
    }
}
