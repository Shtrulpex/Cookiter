package com.example.cookiter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cookiter.R;

public class StartActivity extends AppCompatActivity {

    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReg = (Button)findViewById(R.id.btnReg);

    }

    public void onRegBtnClick(View v){
        Intent i = new Intent(StartActivity.this, RegisterActivity.class);
        startActivity(i);
    }
    public void onSignInBtnClick(View v){
        Intent i = new Intent(StartActivity.this, SignInActivity.class);
        startActivity(i);
    }
}
