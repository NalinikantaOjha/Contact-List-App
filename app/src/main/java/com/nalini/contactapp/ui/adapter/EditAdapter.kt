package com.nalini.contactapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactsEntity

class EditAdapter (val context: Context, val list:MutableList<ContactsEntity>,val onEdit: OnEdit): RecyclerView.Adapter<EditViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditViewHolder {
        val inflater = LayoutInflater.from(context)

        val view1: View = inflater.inflate(R.layout.item_layout_edit, parent, false)
        return EditViewHolder(view1,onEdit)
    }

    override fun onBindViewHolder(holder: EditViewHolder, position: Int) {
        val type=list.get(position)
        holder.setData(type)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}