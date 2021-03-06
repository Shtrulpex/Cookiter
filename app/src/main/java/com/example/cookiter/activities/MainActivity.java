package com.example.cookiter.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.cookiter.R;
import com.example.cookiter.fragments.FeedFragment;
import com.example.cookiter.fragments.ProfileFragment;
import com.example.cookiter.models.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = new UserModel();
        user.setLogin(getIntent().getStringExtra("login"));

        setContentView(R.layout.activity_main2);
        Fragment fragment = new FeedFragment();
        Bundle bundle = new Bundle();
        bundle.putString("login", getIntent().getStringExtra("login"));
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();

        bottomNavigationView= findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }
    private void setFragment(Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putString("login", user.getLogin());
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }
}
