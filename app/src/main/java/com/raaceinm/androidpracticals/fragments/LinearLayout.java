package com.raaceinm.androidpracticals.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raaceinm.androidpracticals.R;

public class LinearLayout extends Fragment {

    public LinearLayout() {
        // Required empty public constructor
    }
    public static LinearLayout newInstance(String param1, String param2) {
        LinearLayout fragment = new LinearLayout();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_linear_layout, container, false);
    }
}