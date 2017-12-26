package com.packtpub.bankingclient.balance.service;

import com.packtpub.bankingclient.security.exception.InvalidRequestException;
import com.packtpub.bankingclient.security.service.LoginService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class BalanceService {

    public String balance() throws InvalidRequestException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-auth-token", LoginService.JWT_TOKEN);
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(LoginService.SERVICE_BASE_URL + "/api/secure/balance", HttpMethod.GET, request, String.class).getBody();
    }
}
