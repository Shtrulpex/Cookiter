package com.example.cookiter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.Button;
import android.widget.Toast;

import com.example.cookiter.R;
import com.example.cookiter.activities.AddRecipeActivity;
import com.example.cookiter.activities.MainActivity;
import com.example.cookiter.activities.StartActivity;

public class ProfileFragment extends Fragment {

    String login;

    public ProfileFragment(){}
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        Button btn = rootView.findViewById(R.id.add);

        login = this.getArguments().getString("login");
        Toast.makeText(this.getActivity().getApplicationContext(), login, Toast.LENGTH_LONG).show();
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(ProfileFragment.this.getActivity(), AddRecipeActivity.class);
                i.putExtra("login", login);
                startActivity(i);
            }
        });
        return rootView;
    }
}
