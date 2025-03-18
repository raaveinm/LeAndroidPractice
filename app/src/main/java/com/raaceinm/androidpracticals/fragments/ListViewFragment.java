package com.raaceinm.androidpracticals.fragments;

import static com.raaceinm.androidpracticals.Tools.ComponentsKt.getGPUDataSet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.raaceinm.androidpracticals.R;
import com.raaceinm.androidpracticals.Tools.GPUs;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ListViewFragment extends Fragment {

    @NotNull
    List<GPUs> listItems = getGPUDataSet();
    private ListView customListView;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout first
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        customListView = view.findViewById(R.id.listViewArray);
        ArrayAdapter<String> customAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, getNames(listItems));

        customListView.setAdapter(customAdapter);
        return view;
    }
    private List<String> getNames(List<GPUs> gpuList){
        List<String> names = new java.util.ArrayList<>();
        for (GPUs gpu : gpuList) {
            names.add(gpu.getName());
        }
        return names;
    }
}



