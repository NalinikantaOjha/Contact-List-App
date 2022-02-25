package com.nalini.contactapp.ui.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.local.ContactNumberRelation
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.ui.iterface.onView
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.item_layout.view.Contacts
import kotlinx.android.synthetic.main.item_layout.view.tvContactName
import kotlinx.android.synthetic.main.item_layout_number_searching.view.*

class SearchContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun setData(numberEntity: NumberEntity){
        itemView.apply {
             Log.d("naliniadapter",tvContactName.toString())

            tvContactName.text = numberEntity.name
            tvPhoneNumber.text=numberEntity.number

        }
    }
}