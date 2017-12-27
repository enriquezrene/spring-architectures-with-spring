package com.packtpub.bankingmobile.api.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by moe on 12/26/17.
 */

public class RetrofitApi {

    private static Retrofit RETROFIT;

    public static final String SERVICE_BASE_URL = "http://192.168.100.4:8080/";

    public static Retrofit getRetrofit() {
        if (RETROFIT == null) {
            getRetrofitClient();
        }
        return RETROFIT;
    }

    private static void getRetrofitClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        RETROFIT = new Retrofit.Builder()
                .baseUrl(SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
