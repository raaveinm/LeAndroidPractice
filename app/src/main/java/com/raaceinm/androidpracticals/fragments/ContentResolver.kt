package com.raaceinm.androidpracticals.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.PopupWindow
import android.widget.Spinner
import com.raaceinm.androidpracticals.R

class ContentResolver : Fragment(), AdapterView.OnItemSelectedListener {
    data class ComponentList(val name: String)
    private val componentNames: List<String> = listOf("CPU", "GPU")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_content_resolver, container, false)
        val spinner: Spinner = view.findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(requireContext(),
            R.array.compArray,android.R.layout.simple_spinner_item).also{adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        return view
    }
    private fun collapseSpinner(spinner: Spinner) {
        try {
            val method = Spinner::class.java.getDeclaredMethod("getPopup")
            method.isAccessible = true
            val popupWindow = method.invoke(spinner) as PopupWindow
            popupWindow.dismiss()
        } catch (e: Exception) { }
    }


    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        val selectedItem = parent?.getItemAtPosition(position).toString()

        if (selectedItem == "GPU") {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, ListViewFragment())
                .commit()
        } else if (selectedItem == "CPU") {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, RecyclerViewFragment())
                .commit()
            collapseSpinner(parent as Spinner)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}