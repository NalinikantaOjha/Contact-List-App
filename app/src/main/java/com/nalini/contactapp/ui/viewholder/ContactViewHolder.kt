package com.nalini.contactapp.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.local.ContactNumberRelation
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.ui.iterface.onView
import kotlinx.android.synthetic.main.item_layout.view.*

class ContactViewHolder(itemView:View,val onView: onView): RecyclerView.ViewHolder(itemView) {
fun setData(contactNumberRelation: ContactNumberRelation){
    itemView.apply {
        tvContactName.text = contactNumberRelation.contactsEntity.name
        Contacts.setOnClickListener {
            onView.onView(contactNumberRelation.contactsEntity)
        }
    }
}
}