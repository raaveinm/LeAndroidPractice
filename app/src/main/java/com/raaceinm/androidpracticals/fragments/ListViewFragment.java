package com.raaceinm.androidpracticals.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.raaceinm.androidpracticals.R;


public class ListViewFragment extends Fragment {


    String[] listItems = {
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5"
    };

    public ListViewFragment() {

    }

    public static ListViewFragment newInstance(String param1, String param2) {
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            System.out.println("Arguments: " + getArguments());
        }

        ListView customListView = getView().findViewById(R.id.listViewArray);
        ArrayAdapter<String> customAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, listItems);
        customListView.setAdapter(customAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }
}