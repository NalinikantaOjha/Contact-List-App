package com.nalini.contactapp.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.local.ContactNumberRelation
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.ui.iterface.OnEdit
import kotlinx.android.synthetic.main.item_layout_edit.view.*

class EditViewHolder(itemView: View,val onEdit: OnEdit): RecyclerView.ViewHolder(itemView) {
    fun setData(contactNumberRelation: ContactNumberRelation){
        itemView.apply {
            tvContactName.text = contactNumberRelation.contactsEntity.name
            btnRadioDelete.isChecked = contactNumberRelation.contactsEntity.track != false

            btnRadioDelete.setOnClickListener {
                onEdit.Delete(contactNumberRelation)
            }
        }
    }
}