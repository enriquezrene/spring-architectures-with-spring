package com.packtpub.bankingmobile.api;

import com.packtpub.bankingmobile.api.balance.domain.Balance;
import com.packtpub.bankingmobile.api.security.domain.Credentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BankApi {

    @POST("/api/public/auth")
    Call<String> login(@Body Credentials credentials);

    @GET("/api/secure/balance")
    Call<Balance> queryBalance(@Header("x-auth-token") String token);
}


