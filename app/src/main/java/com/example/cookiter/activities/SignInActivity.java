package com.example.cookiter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.cookiter.R;
import com.example.cookiter.RestApi;
import com.example.cookiter.models.TrueFalseModel;
import com.example.cookiter.models.UserModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    MaterialEditText loginEmail, pass;
    int response1, response2;
    private static String DB_URI = "https://cookiter.herokuapp.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void signIn(View v){
        loginEmail = findViewById(R.id.logEmailFill);
        pass = findViewById(R.id.passwordFill);

        if(TextUtils.isEmpty(loginEmail.getText().toString())){
            loginEmail.setError("Вы не ввели свой логин");
            return;
        }
        if(TextUtils.isEmpty(pass.getText().toString())){
            pass.setError("Вы не ввели свой пароль");
            return;
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_URI).addConverterFactory(GsonConverterFactory.create()).build();
        RestApi service = retrofit.create(RestApi.class);
        UserModel requestUser = new UserModel();


        requestUser.setPassword(pass.getText().toString().hashCode());
        requestUser.setLogin(loginEmail.getText().toString());
        requestUser.setEmail(loginEmail.getText().toString());

        Call<TrueFalseModel> call = service.getAccessByLog(requestUser.getLogin(), requestUser.getPassword());
        call.enqueue(new Callback<TrueFalseModel>() {
            @Override
            public void onResponse(Call<TrueFalseModel> call, Response<TrueFalseModel> response) {
                response1 = response.body().getResponse();
                if(response.body().getResponse()==1){
                    Toast.makeText(getApplicationContext(), "Вы успешно вошли", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(SignInActivity.this, StartActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onFailure(Call<TrueFalseModel> call, Throwable t) {

            }
        });

        if(response1==0) {
            call = service.getAccessByEmail(requestUser.getEmail(), requestUser.getPassword());
            call.enqueue(new Callback<TrueFalseModel>() {
                @Override
                public void onResponse(Call<TrueFalseModel> call, Response<TrueFalseModel> response) {
                    if (response.body().getResponse() == 1) {
                        Toast.makeText(getApplicationContext(), "Вы успешно вошли", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SignInActivity.this, StartActivity.class);
                        startActivity(i);
                    } else {
                        pass.setError("Неверный логин или пароль");
                    }
                }

                @Override
                public void onFailure(Call<TrueFalseModel> call, Throwable t) {

                }
            });
        }
    }
}
