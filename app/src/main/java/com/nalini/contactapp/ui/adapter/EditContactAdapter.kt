package com.nalini.contactapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.R
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.ui.iterface.OnUpdate
import com.nalini.contactapp.ui.viewholder.EditContactViewHolder
import com.nalini.contactapp.ui.viewholder.NumbersViewHolder

class EditContactAdapter(val context: Context, val list:MutableList<NumberEntity>,val onUpdate: OnUpdate):
    RecyclerView.Adapter<EditContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditContactViewHolder {
        val inflater = LayoutInflater.from(context)

        val view1: View = inflater.inflate(R.layout.item_layout_editcontact, parent, false)
        return EditContactViewHolder(view1,onUpdate)
    }

    override fun onBindViewHolder(holder: EditContactViewHolder, position: Int) {
        val type=list.get(position)
        holder.setData(type)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}