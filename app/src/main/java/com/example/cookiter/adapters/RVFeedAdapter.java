package com.example.cookiter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cookiter.R;
import com.example.cookiter.models.RecipeModel;

import java.util.ArrayList;

public class RVFeedAdapter extends RecyclerView.Adapter<RVFeedAdapter.RVFeedHolder> {
    ArrayList<RecipeModel> data;

    public static class RVFeedHolder extends RecyclerView.ViewHolder{
        TextView author  = (TextView)itemView.findViewById(R.id.author);
        TextView name  = (TextView)itemView.findViewById(R.id.name);

        public RVFeedHolder(View v){
            super(v);
            this.author =  (TextView)v.findViewById(R.id.author);
            this.name = (TextView)v.findViewById(R.id.name);
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
        name.setText(data.get(listPosition).getName());
        author.setText(data.get(listPosition).getAuthor()); }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
