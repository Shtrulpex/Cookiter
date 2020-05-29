package com.example.cookiter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cookiter.R;
import com.example.cookiter.RestApi;
import com.example.cookiter.models.RecipeModel;
import com.example.cookiter.models.TrueFalseModel;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddRecipeActivity extends AppCompatActivity {
    ArrayList<String> ls;
    ArrayList<Integer> idL;
    String[] s=new String[]{" "};
    MaterialEditText name, body;
    private static String DB_URI = "https://cookiter.herokuapp.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipe);
        ls = new ArrayList<>();
        idL = new ArrayList<>();
        ls.add("");
        idL.add(-1);
        name = findViewById(R.id.recipeName);
        body = findViewById(R.id.recipeBody);
    }

    public void addProduct(View v){
        Intent i = new Intent(AddRecipeActivity.this, AddProductActivity.class);
        i.putExtra("ls", ls);
        i.putExtra("idL", idL);
        i.putExtra("login", getIntent().getStringExtra("login"));
        i.putExtra("name", name.getText().toString());
        i.putExtra("body", body.getText().toString());

        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(getIntent().getStringExtra("login"));

        try {
            if (getIntent().getBooleanExtra("k", false)) {
                ls = getIntent().getStringArrayListExtra("ls");
                idL = getIntent().getIntegerArrayListExtra("idL");
                name.setText(getIntent().getStringExtra("name"));
                body.setText(getIntent().getStringExtra("body"));

                s = new String[ls.size()];

                for (int i = 0; i < ls.size(); i++) {
                    s[i] = ls.get(i);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, s);
                ListView lv = findViewById(R.id.productsList);
                lv.setAdapter(adapter);
            }
        }catch(Exception e){
            e.getLocalizedMessage();
        }
    }

    public void addRecipe(View v){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_URI).addConverterFactory(GsonConverterFactory.create()).build();
        RestApi service = retrofit.create(RestApi.class);

        Integer id[] = new Integer[idL.size()];
        for (int i = 0; i < idL.size(); i++) {
            id[i] = idL.get(i);
        }

        RecipeModel recipe = new RecipeModel();
        recipe.setAuthor(getIntent().getStringExtra("login"));
        recipe.setName(name.getText().toString());
        recipe.setRecipe(body.getText().toString());
        recipe.setProducts(id);


        Call<RecipeModel> call = service.createRecipe(recipe);
        call.enqueue(new Callback<RecipeModel>() {
            @Override
            public void onResponse(Call<RecipeModel> call, Response<RecipeModel> response) {
                System.out.println(response.body().getId());
                Integer prId[]=response.body().getProducts();
                for(int i=1;i<prId.length;i++){

                }
            }

            @Override
            public void onFailure(Call<RecipeModel> call, Throwable t) {

            }
        });
    }
}
