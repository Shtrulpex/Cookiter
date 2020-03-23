package com.example.cookiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

public class RegisterActivity extends AppCompatActivity {

    MaterialEditText email, pass, name;

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
    }
}
