package com.packtpub.bankingclient.security;

import com.packtpub.bankingclient.security.exception.InvalidRequestException;
import com.packtpub.bankingclient.security.service.LoginService;
import org.junit.Test;

public class LoginServiceTest {

    @Test
    public void givenValidCredentials_whenLoginHappens_ThenNoExceptionsThrown() throws Exception {
        String validUsername = "rene";
        String validPassword = "rene";

        new LoginService().doLogin(validUsername, validPassword);
    }

    @Test(expected = InvalidRequestException.class)
    public void givenInvalidCredentials_whenLoginHappens_AnExceptionIsExpected() throws Exception {
        String invalidUsername = "foo";
        String invalidPassword = "bar";

        new LoginService().doLogin(invalidUsername, invalidPassword);
    }
}
