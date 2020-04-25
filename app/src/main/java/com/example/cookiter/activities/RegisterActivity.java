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

public class RegisterActivity extends AppCompatActivity {

    MaterialEditText email, pass, name;
    private static String DB_URI = "https://cookiter.herokuapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.emailFill);
        pass = findViewById(R.id.passwordFill);
        name = findViewById(R.id.loginFill);
    }

    public void onSignInClick(View v){
        Intent i = new Intent(RegisterActivity.this, SignInActivity.class);
        startActivity(i);
    }

    public void onRegBtn(View v){
        if(TextUtils.isEmpty(email.getText().toString())){
            Toast.makeText(this, "Введите свою почту", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(name.getText().toString())){
            Toast.makeText(this, "Придумайте логин", Toast.LENGTH_LONG).show();
            return;
        }
        if(pass.getText().toString().length()<5){
            Toast.makeText(this, "Придумайте пароль подлиннее(не менее 6 символов)", Toast.LENGTH_LONG).show();
            return;
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_URI).addConverterFactory(GsonConverterFactory.create()).build();
        RestApi service = retrofit.create(RestApi.class);
        UserModel user = new UserModel();

        user.setEmail(email.getText().toString());
        user.setLogin(name.getText().toString());
        user.setPassword(pass.getText().toString().hashCode());

        Call<TrueFalseModel> call = service.create(user);
        call.enqueue(new Callback<TrueFalseModel>() {
            @Override
            public void onResponse(Call<TrueFalseModel> call, Response<TrueFalseModel> response) {
                if(response.body().response==1){
                    Toast.makeText(getApplicationContext(), "Вы успешно зарегистрировались!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegisterActivity.this, SignInActivity.class);
                    startActivity(i);
                } else Toast.makeText(getApplicationContext(), "К сожалению такой логин уже существует, поменяйте его.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<TrueFalseModel> call, Throwable t) {

            }
        });
    }
}
