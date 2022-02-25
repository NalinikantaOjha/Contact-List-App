package com.nalini.contactapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.ui.iterface.OnRemove
import com.nalini.contactapp.ui.viewholder.RemoveViewHolder

class RemoveAdapter (val context: Context, val list:MutableList<ContactsEntity>, val onRemove: OnRemove):RecyclerView.Adapter<RemoveViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoveViewHolder {
        val inflater = LayoutInflater.from(context)

        val view1: View = inflater.inflate(R.layout.item_layout_edit, parent, false)
        return RemoveViewHolder(view1,onRemove)
    }

    override fun onBindViewHolder(holder: RemoveViewHolder, position: Int) {
        val type=list.get(position)
        holder.setData(type)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}