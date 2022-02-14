package com.nalini.contactapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.local.ContactsEntity
import kotlinx.android.synthetic.main.item_layout.view.*

class ContactViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
fun setData(contactsEntity: ContactsEntity){
    itemView.apply {
        tvContactName.text = contactsEntity.name
    }
}
}