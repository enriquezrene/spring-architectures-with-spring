package com.packtpub.bankingclient.security.service;

import com.packtpub.bankingclient.security.exception.InvalidRequestException;
import com.packtpub.bankingclient.security.headers.BasicAuthHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public class LoginService {

    public static final String SERVICE_BASE_URL = "http://localhost:8080/";
    public static HttpHeaders AUTH_HEADERS = null;

    public void doLogin(String username, String password) throws InvalidRequestException {
        AUTH_HEADERS = new BasicAuthHeaders(username, password).asHttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(AUTH_HEADERS);
        try {
            restTemplate.exchange(SERVICE_BASE_URL + "ping", HttpMethod.GET, request, Void.class);
        } catch (HttpClientErrorException e) {
            throw new InvalidRequestException("An exception happened during the request, error code: " + e.getStatusCode());
        }
    }
}
