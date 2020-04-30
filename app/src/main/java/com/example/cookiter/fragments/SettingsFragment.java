package com.example.cookiter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;

import com.example.cookiter.R;

public class SettingsFragment extends Fragment {
    public SettingsFragment(){}
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}
