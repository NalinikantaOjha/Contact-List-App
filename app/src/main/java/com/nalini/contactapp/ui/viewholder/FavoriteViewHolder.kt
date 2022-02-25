package com.nalini.contactapp.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.ui.iterface.onView
import kotlinx.android.synthetic.main.item_layout_edit.view.*

class FavoriteViewHolder(intemView: View,val onView: onView):RecyclerView.ViewHolder(intemView) {
    fun setData(contactsEntity: ContactsEntity){
        itemView.apply {
            tvContactName.text = contactsEntity.name
            btnRadioDelete.isChecked = contactsEntity.star != false
            ContactsF.setOnClickListener {
               // onView.onView(contactsEntity)
            }



        }
    }
}