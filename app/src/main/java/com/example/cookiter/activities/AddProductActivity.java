package com.example.cookiter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cookiter.R;
import com.example.cookiter.RestApi;
import com.example.cookiter.models.ProductsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddProductActivity  extends AppCompatActivity {

    private static String DB_URI = "https://cookiter.herokuapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_addproduct);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_URI).addConverterFactory(GsonConverterFactory.create()).build();
        RestApi service = retrofit.create(RestApi.class);

        Call<List<ProductsModel>> call = service.getAllProducts();
        call.enqueue(new Callback<List<ProductsModel>>() {
            @Override
            public void onResponse(Call<List<ProductsModel>> call, final Response<List<ProductsModel>> response) {
                String[] s = new String[response.body().size()];
                SortedSet<String> sortedSet = new TreeSet<>();

                for(int i=0;i<s.length; i++){
                       sortedSet.add(response.body().get(i).getName());
                }
                sortedSet.toArray(s);
                ListView lv = findViewById(R.id.listview);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, s);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
                        Toast.makeText(getApplicationContext(), ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(AddProductActivity.this, AddRecipeActivity.class);
                        ArrayList<String> ls = getIntent().getStringArrayListExtra("ls");
                        ArrayList<Integer> idL = getIntent().getIntegerArrayListExtra("idL");

                        for(int j = 0;j<response.body().size();j++){
                            if(response.body().get(j).getName().equals(((TextView)view).getText())){
                                idL.add(response.body().get(j).getId());
                                Toast.makeText(getApplicationContext(), (response.body().get(j).getId())+"", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }

                        ls.add(((TextView)view).getText().toString());

                        i.putExtra("ls", ls);
                        i.putExtra("idL", idL);
                        i.putExtra("k", true);
                        i.putExtra("login", getIntent().getStringExtra("login"));
                        i.putExtra("name", getIntent().getStringExtra("name"));
                        i.putExtra("body", getIntent().getStringExtra("body"));
                        startActivity(i);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<ProductsModel>> call, Throwable t) {

            }
        });

    }
}
