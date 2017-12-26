package com.packtpub.bankingclient.balance.service;

import com.packtpub.bankingclient.security.exception.InvalidRequestException;
import com.packtpub.bankingclient.security.service.LoginService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class BalanceService {

    public String balance() throws InvalidRequestException {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(LoginService.AUTH_HEADERS);
        try {
            return restTemplate.exchange(LoginService.SERVICE_BASE_URL + "balance", HttpMethod.GET, request, String.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new InvalidRequestException("An exception happened during the request, error code: " + e.getStatusCode());
        }
    }
}
