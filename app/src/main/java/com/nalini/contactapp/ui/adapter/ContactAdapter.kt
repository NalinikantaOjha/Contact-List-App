package com.nalini.contactapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactNumberRelation
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.ui.iterface.onView
import com.nalini.contactapp.ui.viewholder.ContactViewHolder

class ContactAdapter(val context: Context,val list:MutableList<ContactNumberRelation>,val onView: onView):RecyclerView.Adapter<ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(context)

        val view1: View = inflater.inflate(R.layout.item_layout, parent, false)
        return ContactViewHolder(view1,onView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val type=list.get(position)
        holder.setData(type)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}