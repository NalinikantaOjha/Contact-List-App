package com.nalini.contactapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactNumberRelation
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.ui.iterface.onView
import com.nalini.contactapp.ui.viewholder.ContactViewHolder
import com.nalini.contactapp.ui.viewholder.SearchContactViewHolder

class SearchContactAdapter(val context: Context, val list:MutableList<NumberEntity>):
    RecyclerView.Adapter<SearchContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchContactViewHolder {
        val inflater = LayoutInflater.from(context)

        val view1: View = inflater.inflate(R.layout.item_layout_number_searching, parent, false)

        return SearchContactViewHolder(view1)
    }

    override fun onBindViewHolder(holder: SearchContactViewHolder, position: Int) {
        val type=list.get(position)
        holder.setData(type)
    }

    override fun getItemCount(): Int {
        return list.size
        Log.d("naliniadapter",list.size.toString())
    }
}