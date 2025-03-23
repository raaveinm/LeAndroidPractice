package com.raaceinm.androidpracticals.Tools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raaceinm.androidpracticals.R

class RecyclerViewAdapter(private val arrayList:
                          ArrayList<CPUItem>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyRow>() {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyRow {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item,
                parent, false)
        return MyRow(view)
    }

    override fun onBindViewHolder(holder: MyRow, position: Int) {
        holder.textView.text = arrayList[position].name
        Glide.with(holder.itemView.context).
        load(arrayList[position].imageUrl)
            .into(holder.imageView)
    }

    override fun getItemCount() = arrayList.size

    class MyRow(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val textView:
                TextView = itemView.findViewById(R.id.textView)
        val imageView:
                ImageView = itemView.findViewById(R.id.imageView)
    }
}