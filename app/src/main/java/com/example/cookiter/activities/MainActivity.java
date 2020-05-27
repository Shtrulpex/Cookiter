package com.example.cookiter.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.cookiter.R;
import com.example.cookiter.RestApi;
import com.example.cookiter.fragments.FeedFragment;
import com.example.cookiter.fragments.ProfileFragment;
import com.example.cookiter.fragments.SettingsFragment;
import com.example.cookiter.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String DB_URI = "https://cookiter.herokuapp.com/";
    BottomNavigationView bottomNavigationView;
    UserModel user;
    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId()){
                case R.id.profile:
                    setFragment(new ProfileFragment());
                    return true;
                case R.id.feed:
                    setFragment(new FeedFragment());
                    return true;
                case R.id.preferences:
                    setFragment(new SettingsFragment());
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, new FeedFragment());
        transaction.commit();

        bottomNavigationView= findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        getUser(getIntent().getStringExtra("login"), getIntent().getBooleanExtra("emailLog", false));
    }
    private void setFragment(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString("login", user.getLogin());
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }
    private void getUser(String login, boolean emailLog){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_URI).addConverterFactory(GsonConverterFactory.create()).build();
        final RestApi service = retrofit.create(RestApi.class);

        if(emailLog){
            Call<UserModel> call = service.getUserByEmail(login);
            call.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    user=response.body();
                    System.out.println(user.getLogin());
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {

                }
            });
        }else{
            Call<UserModel> call = service.getUserByLog(login);
            call.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    user=response.body();
                    System.out.println(user.getEmail());
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {

                }
            });
        }
    }
}
