package com.nalini.contactapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.local.ContactsEntity
import kotlinx.android.synthetic.main.item_layout_edit.view.*

class EditViewHolder(itemView: View,val onEdit: OnEdit): RecyclerView.ViewHolder(itemView) {
    fun setData(contactsEntity: ContactsEntity){
        itemView.apply {
            tvContactName.text = contactsEntity.name



            btnRadioDelete.setOnClickListener {
                onEdit.Delete(contactsEntity)
            }
        }
    }
}