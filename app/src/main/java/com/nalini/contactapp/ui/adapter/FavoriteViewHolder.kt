package com.nalini.contactapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.local.ContactsEntity
import kotlinx.android.synthetic.main.item_layout_edit.view.*

class FavoriteViewHolder(intemView: View):RecyclerView.ViewHolder(intemView) {
    fun setData(contactsEntity: ContactsEntity){
        itemView.apply {
            tvContactName.text = contactsEntity.name
            btnRadioDelete.isChecked = contactsEntity.star != false


        }
    }
}