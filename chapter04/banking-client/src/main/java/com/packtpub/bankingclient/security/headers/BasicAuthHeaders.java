package com.packtpub.bankingclient.security.headers;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

public class BasicAuthHeaders {

    private final String username;
    private final String password;

    public BasicAuthHeaders(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return "Authorization";
    }

    public String getValue() {
        String plainCredentials = username + ":" + password;
        byte[] plainCredentialsAsBytes = plainCredentials.getBytes();
        byte[] base64CredentialsAsBytes = Base64.encodeBase64(plainCredentialsAsBytes);
        String base64Credentials = new String(base64CredentialsAsBytes);
        return "Basic " + base64Credentials;
    }

    public HttpHeaders asHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(getName(), getValue());
        return httpHeaders;
    }


}
