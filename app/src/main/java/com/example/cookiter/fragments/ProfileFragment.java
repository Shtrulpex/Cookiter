package com.example.cookiter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookiter.R;
import com.example.cookiter.RestApi;
import com.example.cookiter.activities.AddRecipeActivity;
import com.example.cookiter.activities.MainActivity;
import com.example.cookiter.activities.StartActivity;
import com.example.cookiter.models.RecipeModel;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private static String DB_URI = "https://cookiter.herokuapp.com/";
    String login;
    ArrayList<RecipeModel> recipes;
    private static RecyclerView.Adapter adapter;
    RecyclerView rv;

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

        rv = rootView.findViewById(R.id.rv1);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        recipes = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_URI).addConverterFactory(GsonConverterFactory.create()).build();
        RestApi service = retrofit.create(RestApi.class);



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
