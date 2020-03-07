package com.example.cookiter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    Button btnReg, btnSignIn;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnReg = (Button)findViewById(R.id.btnReg);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

    }

    public void onRegbtnClick(View v){
        AlertDialog.Builder alD = new AlertDialog.Builder(this);
        alD.setTitle("Регистрация");
        alD.setMessage("Введите данные");

        LayoutInflater inflater = LayoutInflater.from(this);
        View reg_window = inflater.inflate(R.layout.register_window, null);
        alD.setView(reg_window);

        MaterialEditText email = reg_window.findViewById(R.id.emailFill);
        MaterialEditText login = reg_window.findViewById(R.id.loginFill);
        MaterialEditText pass = reg_window.findViewById(R.id.passwordFill);
    }
}
