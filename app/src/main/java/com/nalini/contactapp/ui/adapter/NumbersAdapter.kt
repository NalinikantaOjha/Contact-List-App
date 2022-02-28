package com.nalini.contactapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.R
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.ui.iterface.OnCall
import com.nalini.contactapp.ui.viewholder.NumbersViewHolder

class NumbersAdapter(val context: Context, val list:MutableList<NumberEntity>,val onCall: OnCall):
    RecyclerView.Adapter<NumbersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersViewHolder {
        val inflater = LayoutInflater.from(context)

        val view1: View = inflater.inflate(R.layout.numbers_view_item_layout, parent, false)
        return NumbersViewHolder(view1,onCall)
    }

    override fun onBindViewHolder(holder: NumbersViewHolder, position: Int) {
        val type=list.get(position)
        holder.setData(type)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}