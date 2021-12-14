package com.smartstake.api.services;

import com.smartstake.api.dto.AuthServiceLoginResponseDTO;
import com.smartstake.api.dto.ClientDTO;
import com.smartstake.api.dto.LoginDTO;
import com.smartstake.api.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class AuthService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ClientService clientService;

//    private final String AUTH_ENDPOINT = "http://localhost:8080/stubs/auth";
    private final String AUTH_ENDPOINT = "https://smartstakeauthservice.herokuapp.com";

//    public ResponseEntity<AuthServiceLoginResponseDTO> login(LoginDTO loginDTO) {
//        if (isNull(loginDTO.getUsername()) || isNull(loginDTO.getPassword())){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//
//        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
//        RestTemplate rt = restTemplateBuilder.build();
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        messageConverters.add(converter);
//        rt.setMessageConverters(messageConverters);
//
////        restTemplate.setMessageConverters(messageConverters);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Collections.singletonList(MediaType.ALL));
//        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
//        form.add("username", loginDTO.getUsername());
//        form.add("password", loginDTO.getPassword());
//        HttpEntity<MultiValueMap> entity = new HttpEntity(form, headers);
////        return restTemplate.exchange(AUTH_ENDPOINT + "/login", HttpMethod.POST, entity, AuthServiceLoginResponseDTO.class);
//        return restTemplate.postForEntity(AUTH_ENDPOINT + "/login", entity, AuthServiceLoginResponseDTO.class);
//    }







    public ResponseEntity<AuthServiceLoginResponseDTO> login(LoginDTO loginDTO) {
        if (isNull(loginDTO.getUsername()) || isNull(loginDTO.getPassword())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("username", loginDTO.getUsername());
        form.add("password", loginDTO.getPassword());
        HttpEntity<HashMap> entity = new HttpEntity(form, headers);
//        return restTemplate.exchange(AUTH_ENDPOINT + "/login", HttpMethod.POST, entity, AuthServiceLoginResponseDTO.class);
        return restTemplate.postForEntity(AUTH_ENDPOINT + "/login", entity, AuthServiceLoginResponseDTO.class);
    }

    public ResponseEntity<AuthServiceLoginResponseDTO> register(RegisterDTO registerRequest) {
        if (isNull(registerRequest.getEmail()) ||
                isNull(registerRequest.getFirstName()) ||
                isNull(registerRequest.getLastName()) ||
                isNull(registerRequest.getPassword()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

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


/*
* restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder.build();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
*
* */



/*

// request url
String url = "https://reqres.in/api/login";

// create an instance of RestTemplate
RestTemplate restTemplate = new RestTemplate();

// create headers
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);
headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
// add basic authentication
headers.setBasicAuth("username", "password");

// build the request
HttpEntity request = new HttpEntity(headers);
// send POST request
ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

// check response
if (response.getStatusCode() == HttpStatus.OK) {
    System.out.println("Login Successful");
} else {
    System.out.println("Login Failed");
}


 */