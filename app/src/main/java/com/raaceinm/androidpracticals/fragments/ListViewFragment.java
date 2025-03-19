package com.raaceinm.androidpracticals.fragments;

import static com.raaceinm.androidpracticals.Tools.ComponentsKt.getGPUDataSet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.raaceinm.androidpracticals.R;
import com.raaceinm.androidpracticals.Tools.GPUs;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ListViewFragment extends Fragment {

    @NotNull
    List<GPUs> listItems = getGPUDataSet();
    private ListView customListView;
    private ArrayAdapter<String> customAdapter;

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
        customAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, getNames(listItems));

        customListView.setAdapter(customAdapter);


        return view;
    }

    private List<String> getNames(List<GPUs> gpuList) {
        List<String> names = new java.util.ArrayList<>();
        for (GPUs gpu : gpuList) {
            names.add(gpu.getName());
        }
        return names;
    }

    public void updateList() {
        if (customAdapter != null) {
            customAdapter.clear();
            customAdapter.addAll(getNames(listItems));
            customAdapter.notifyDataSetChanged();
        }
    }
}
