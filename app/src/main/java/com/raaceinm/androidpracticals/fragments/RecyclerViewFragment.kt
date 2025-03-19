package com.raaceinm.androidpracticals.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raaceinm.androidpracticals.R
import com.raaceinm.androidpracticals.Tools.CPUItem
import com.raaceinm.androidpracticals.Tools.RecyclerViewAdapter
import com.raaceinm.androidpracticals.Tools.getCPUDataSet
import com.raaceinm.androidpracticals.Tools.getCPUImageDataSet
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

class RecyclerViewFragment : Fragment() {
    private var name = getCPUDataSet()
    private var ImageUrl = getCPUImageDataSet()

    private fun generateList(): ArrayList<CPUItem> {
        val arrayList = ArrayList<CPUItem>()
        for (i in name.indices) {
            arrayList.add(CPUItem(name[i], ImageUrl[i]))
        }
        return arrayList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_recycler_view, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecyclerViewAdapter(generateList())

        return view
    }
}