package com.raaceinm.androidpracticals.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.raaceinm.androidpracticals.R
import com.raaceinm.androidpracticals.Tools.GPUs
import com.raaceinm.androidpracticals.Tools.getGPUDataSet

class ListViewFragment : Fragment() {

    private var customListView: ListView? = null
    private var customAdapter: ArrayAdapter<String>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_list_view, container, false)

        customListView = view.findViewById(R.id.listViewArray)

        val listItems: List<GPUs> = getGPUDataSet()
        customAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            getNames(listItems)
        )
        customListView?.adapter = customAdapter
        return view
    }

    private fun getNames(gpuList: List<GPUs>): List<String> {
        val names = mutableListOf<String>()
        for (card in gpuList) {
            names.add(card.name)
        }
        return names
    }

    fun updateList() {
        val listItems: List<GPUs> = getGPUDataSet()

        customAdapter?.let { adapter ->
            adapter.clear()
            adapter.addAll(getNames(listItems))
            adapter.notifyDataSetChanged()
        }
    }
}