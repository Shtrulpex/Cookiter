package com.example.cookiter.activities;

import androidx.appcompat.app.AppCompatActivity;

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
    private static String DB_URI = "https://cookiter.herokuapp.com/";
    int response1;
    int response2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void signIn(View v){
        loginEmail = findViewById(R.id.logEmailFill);
        pass = findViewById(R.id.passwordFill);

        if(TextUtils.isEmpty(loginEmail.getText().toString())||TextUtils.isEmpty(pass.getText().toString())){
            Toast.makeText(this, "Вы заполнили не все поля", Toast.LENGTH_LONG).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_URI).addConverterFactory(GsonConverterFactory.create()).build();
        RestApi service = retrofit.create(RestApi.class);
        UserModel user = new UserModel();

        user.setPassword(pass.getText().toString().hashCode());
        user.setLogin(loginEmail.getText().toString());
        user.setEmail(loginEmail.getText().toString());

        Call<TrueFalseModel> call = service.getAccessByLog(user);
        call.enqueue(new Callback<TrueFalseModel>() {
            @Override
            public void onResponse(Call<TrueFalseModel> call, Response<TrueFalseModel> response) {
                response1 = response.body().response;
                System.out.println(response.raw());
                System.out.println(response1);
            }

            @Override
            public void onFailure(Call<TrueFalseModel> call, Throwable t) {

            }
        });
    }
}
