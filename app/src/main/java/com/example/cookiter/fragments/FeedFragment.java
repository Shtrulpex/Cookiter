package com.example.cookiter.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookiter.R;
import com.example.cookiter.RestApi;
import com.example.cookiter.adapters.RVFeedAdapter;
import com.example.cookiter.models.ProductsModel;
import com.example.cookiter.models.RecipeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedFragment extends Fragment {

    private static String DB_URI = "https://cookiter.herokuapp.com/";

    public FeedFragment(){}
    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    String login;
    ArrayList<RecipeModel> recipes;
    private static RecyclerView.Adapter adapter;
    RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        rv = view.findViewById(R.id.rv);
        login = this.getArguments().getString("login");
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        recipes = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_URI).addConverterFactory(GsonConverterFactory.create()).build();
        RestApi service = retrofit.create(RestApi.class);

        Call<List<RecipeModel>> call = service.getAllRecipes();
        call.enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                for(int i=0;i<response.body().size();i++){
                    recipes.add(response.body().get(i));
                }
                adapter = new RVFeedAdapter(recipes, FeedFragment.this.getActivity(), login);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {

            }
        });



        return view;
    }
}
