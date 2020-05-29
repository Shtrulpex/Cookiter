package com.example.cookiter.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookiter.R;
import com.example.cookiter.RestApi;
import com.example.cookiter.adapters.RVFeedAdapter;
import com.example.cookiter.fragments.ProfileFragment;
import com.example.cookiter.models.RecipeModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OtherProfileActivity extends AppCompatActivity {

    String login;
    String profile;
    private static String DB_URI = "https://cookiter.herokuapp.com/";
    ArrayList<RecipeModel> recipes;
    private static RecyclerView.Adapter adapter;
    RecyclerView rv;
    TextView profileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        login = getIntent().getStringExtra("login");
        profile = getIntent().getStringExtra("profile");
        profileName = findViewById(R.id.profileName1);
        profileName.setText(profile);
        recipes = new ArrayList<>();

        rv = findViewById(R.id.rv2);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_URI).addConverterFactory(GsonConverterFactory.create()).build();
        RestApi service = retrofit.create(RestApi.class);

        Call<List<RecipeModel>> call = service.getAllRecipes();
        call.enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                for(int i=0;i<response.body().size();i++){
                    if(("Автор: "+response.body().get(i).getAuthor()).equals(profile)) {
                        recipes.add(response.body().get(i));
                    }
                }
                adapter = new RVFeedAdapter(recipes, OtherProfileActivity.this, login);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {

            }
        });
    }
}
