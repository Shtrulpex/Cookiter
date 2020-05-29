package com.example.cookiter.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookiter.R;
import com.example.cookiter.RestApi;
import com.example.cookiter.activities.MainActivity;
import com.example.cookiter.activities.OtherProfileActivity;
import com.example.cookiter.models.ProductsModel;
import com.example.cookiter.models.RecipeModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RVFeedAdapter extends RecyclerView.Adapter<RVFeedAdapter.RVFeedHolder> {
    ArrayList<RecipeModel> data;
    private static String DB_URI = "https://cookiter.herokuapp.com/";
    String s="";
    Context context;
    String login;

    public static class RVFeedHolder extends RecyclerView.ViewHolder{
        TextView author  = (TextView)itemView.findViewById(R.id.author);
        TextView name  = (TextView)itemView.findViewById(R.id.name);
        TextView prodText = (TextView)itemView.findViewById(R.id.prodText);
        CardView cv = (CardView)itemView.findViewById(R.id.card_view);

        public RVFeedHolder(View v){
            super(v);
            this.author =  (TextView)v.findViewById(R.id.author);
            this.name = (TextView)v.findViewById(R.id.name);
            this.prodText = (TextView)v.findViewById(R.id.prodText);
            this.cv = (CardView)v.findViewById(R.id.card_view);
        }
    }
    public RVFeedAdapter(ArrayList<RecipeModel> data, Context context, String login){
        this.data = data;
        this.context = context;
        this.login = login;
    }
    public RVFeedAdapter(ArrayList<RecipeModel> data, Context context){
        this.data = data;
        this.context = context;

    }

    @Override
    public RVFeedHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipecard, parent, false);

        RVFeedHolder rVFeedHolder = new RVFeedHolder(view);
        return rVFeedHolder;
    }
    @Override
    public void onBindViewHolder(final RVFeedHolder holder, final int listPosition) {

        TextView name = holder.name;
        TextView author = holder.author;
        final TextView prodText = holder.prodText;
        CardView cv = holder.cv;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_URI).addConverterFactory(GsonConverterFactory.create()).build();
        RestApi service = retrofit.create(RestApi.class);

        Call<List<ProductsModel>> call = service.getAllProducts();

        call.enqueue(new Callback<List<ProductsModel>>() {
            @Override
            public void onResponse(Call<List<ProductsModel>> call, Response<List<ProductsModel>> response) {
                System.out.println(response.raw());
                for(int i = 0;i<response.body().size();i++) {
                    for (int j = 0; j < data.get(data.size() - listPosition - 1).getProducts().length; j++) {
                        if (response.body().get(i).getId() == data.get(data.size() - listPosition - 1).getProducts()[j]) {
                            s += response.body().get(i).getName() + "\n";
                        }
                    }
                }
                prodText.setText("Необходимые продукты:\n"+s);
                s="";
            }

            @Override
            public void onFailure(Call<List<ProductsModel>> call, Throwable t) {

            }
        });

        name.setText("Название: "+data.get(data.size()-listPosition-1).getName());
        author.setText("Автор: "+data.get(data.size()-listPosition-1).getAuthor());
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i =data.size()-1;i>=0;i--){
                    if(((TextView)v.findViewById(R.id.name)).getText().toString().equals("Название: "+data.get(i).getName())){
                        showAlert(data.get(i), listPosition);
                        System.out.println("otlichno");
                    }else System.out.println(((TextView)v.findViewById(R.id.name)).getText().toString());
                }
            }
        });
    }

    private void showAlert(RecipeModel recipeModel, final int listPosition) {
        AlertDialog.Builder ad = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View recipe_window = inflater.inflate(R.layout.recipe_window, null);
        TextView author = recipe_window.findViewById(R.id.author_win);
        final TextView products = recipe_window.findViewById(R.id.prod);
        TextView recipe = recipe_window.findViewById(R.id.rec);
        TextView name = recipe_window.findViewById(R.id.Name);
        author.setText("Автор: "+recipeModel.getAuthor());
        recipe.setText("Рецепт: "+recipeModel.getRecipe());
        name.setText(recipeModel.getName());

        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_URI).addConverterFactory(GsonConverterFactory.create()).build();
        RestApi service = retrofit.create(RestApi.class);

        Call<List<ProductsModel>> call = service.getAllProducts();

        call.enqueue(new Callback<List<ProductsModel>>() {
            @Override
            public void onResponse(Call<List<ProductsModel>> call, Response<List<ProductsModel>> response) {
                System.out.println(response.raw());
                for(int i = 0;i<response.body().size();i++) {
                    for (int j = 0; j < data.get(data.size() - listPosition - 1).getProducts().length; j++) {
                        if (response.body().get(i).getId() == data.get(data.size() - listPosition - 1).getProducts()[j]) {
                            s += response.body().get(i).getName() + "\n";
                        }
                    }
                }
                products.setText("Необходимые продукты:\n"+s);
                s="";
            }
            @Override
            public void onFailure(Call<List<ProductsModel>> call, Throwable t) {

            }
        });

        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, OtherProfileActivity.class);
                i.putExtra("login", login);
                i.putExtra("profile", ((TextView)v).getText().toString());
                context.startActivity(i);
            }
        });

        ad.setView(recipe_window);
        ad.show();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
