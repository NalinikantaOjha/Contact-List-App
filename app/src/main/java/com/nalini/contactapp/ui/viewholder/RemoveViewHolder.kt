package com.nalini.contactapp.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.ui.iterface.OnRemove
import kotlinx.android.synthetic.main.item_layout_edit.view.*

class RemoveViewHolder(itemView: View,val onRemove: OnRemove):RecyclerView.ViewHolder(itemView) {
    fun setData(contactsEntity: ContactsEntity){
        itemView.apply {
            tvContactName.text = contactsEntity.name
            btnRadioDelete.isChecked = contactsEntity.star != true
            btnRadioDelete.setOnClickListener {
                onRemove.OnRemove(contactsEntity)
            }

        }
    }
}