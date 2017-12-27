package com.packtpub.bankingmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.packtpub.bankingmobile.api.BankApi;
import com.packtpub.bankingmobile.api.client.RetrofitApi;
import com.packtpub.bankingmobile.api.security.domain.Credentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    private EditText usernameText, passwordText;
    private Button loginButton;

    public static String JWT_TOKEN = "JWT_TOKEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);


        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String username = usernameText.getText().toString();
                final String password = passwordText.getText().toString();
                try {
                    BankApi api = RetrofitApi.getRetrofit().create(BankApi.class);
                    Call<String> call = api.login(new Credentials(username, password));
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String token = response.body();
                            Intent intent = new Intent(LoginActivity.this, BalanceActivity.class);
                            intent.putExtra(JWT_TOKEN, token);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    usernameText.setText(e.getMessage());
                }

            }
        });

    }


}
//
//class LoginTask extends AsyncTask<String, Void, String> {
//
//    @Override
//    protected String doInBackground(String... strings) {
//        new LoginService().doLogin(strings[0], strings[1]);
//        return LoginService.JWT_TOKEN;
//    }
//
//}

