package com.example.cookiter.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookiter.R;
import com.example.cookiter.adapters.RVFeedAdapter;
import com.example.cookiter.models.RecipeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class FeedFragment extends Fragment {
    public FeedFragment(){}
    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    ArrayList<RecipeModel> recipes;
    private static RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        RecyclerView rv = view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        recipes = new ArrayList<>();
        recipes.add(new RecipeModel("Варить 10 минут", "Варёное яйцо", "Traggy", new String[]{"яйцо"}));
        recipes.add(new RecipeModel("Варить 5 минут", "Варёное яйцо", "Traggy", new String[]{"яйцо"}));
        recipes.add(new RecipeModel("Варить 1 минут", "Варёное яйцо", "Traggy", new String[]{"яйцо"}));

        adapter = new RVFeedAdapter(recipes);
        rv.setAdapter(adapter);

        return view;
    }
}
