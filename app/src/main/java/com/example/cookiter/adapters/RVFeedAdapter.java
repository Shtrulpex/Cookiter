package com.example.cookiter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cookiter.R;
import com.example.cookiter.RestApi;
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

    public static class RVFeedHolder extends RecyclerView.ViewHolder{
        TextView author  = (TextView)itemView.findViewById(R.id.author);
        TextView name  = (TextView)itemView.findViewById(R.id.name);
        TextView prodText = (TextView)itemView.findViewById(R.id.prodText);

        public RVFeedHolder(View v){
            super(v);
            this.author =  (TextView)v.findViewById(R.id.author);
            this.name = (TextView)v.findViewById(R.id.name);
            this.prodText = (TextView)v.findViewById(R.id.prodText);
        }
    }
    public RVFeedAdapter(ArrayList<RecipeModel> data){
        this.data = data;
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

        Retrofit retrofit = new Retrofit.Builder().baseUrl(DB_URI).addConverterFactory(GsonConverterFactory.create()).build();
        RestApi service = retrofit.create(RestApi.class);

        Call<String[]> call = service.getPrById(data.get(data.size()-listPosition-1).getProducts());

        call.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                for(int i = 0;i<response.body().length;i++){
                        s+=response.body()[i]+"\n";
                }
                prodText.setText("Необходимые продукты:\n"+s);
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {

            }
        });

        name.setText("Название: "+data.get(data.size()-listPosition-1).getName());
        author.setText("Автор: "+data.get(data.size()-listPosition-1).getAuthor()); }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
