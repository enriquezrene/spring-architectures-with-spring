package com.packtpub.bankingclient.security.service;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class LoginService {

    public static final String SERVICE_BASE_URL = "http://localhost:8080";
    public static String JWT_TOKEN = null;

    public void doLogin(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        String body = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);
        HttpEntity<String> request = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(SERVICE_BASE_URL + "/api/public/auth", HttpMethod.POST, request, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An exception happened during the request");
        }

        if (HttpStatus.OK.equals(response.getStatusCode())) {
            JWT_TOKEN = response.getBody();
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
