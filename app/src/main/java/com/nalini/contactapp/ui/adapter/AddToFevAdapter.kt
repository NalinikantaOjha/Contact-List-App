package com.nalini.contactapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactsEntity

class AddToFevAdapter(val context: Context, val list:MutableList<ContactsEntity>,val onFavorite: OnFavorite):RecyclerView.Adapter<AddToFevViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToFevViewHolder {
        val inflater = LayoutInflater.from(context)

        val view1: View = inflater.inflate(R.layout.item_layout_edit, parent, false)
        return AddToFevViewHolder(view1,onFavorite)
    }

    override fun onBindViewHolder(holder: AddToFevViewHolder, position: Int) {
        val type=list.get(position)
        holder.setData(type)
    }

    override fun getItemCount(): Int {
return list.size
    }
}