package com.packtpub.bankingmobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.packtpub.bankingmobile.api.BankApi;
import com.packtpub.bankingmobile.api.balance.domain.Balance;
import com.packtpub.bankingmobile.api.client.RetrofitApi;
import com.packtpub.bankingmobile.api.security.domain.Credentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BalanceActivity extends Activity {

    private TextView balanceTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        balanceTextView = findViewById(R.id.fullscreen_content);
        String token = getIntent().getStringExtra(LoginActivity.JWT_TOKEN);
        BankApi api = RetrofitApi.getRetrofit().create(BankApi.class);
        Call<Balance> call = api.queryBalance(token);
        call.enqueue(new Callback<Balance>() {

            @Override
            public void onResponse(Call<Balance> call, Response<Balance> response) {
                balanceTextView.setText("Your balance is: "+response.body().getBalance());
            }

            @Override
            public void onFailure(Call<Balance> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


}
