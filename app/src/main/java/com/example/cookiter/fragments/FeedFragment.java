package com.example.cookiter.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.example.cookiter.R;

public class FeedFragment extends Fragment {
    public FeedFragment(){}
    public static FeedFragment newInstance() {
        return new FeedFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }
}
