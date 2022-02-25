package com.nalini.contactapp.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.local.NumberEntity
import kotlinx.android.synthetic.main.numbers_view_item_layout.view.*

class NumbersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun setData(numbersEntity: NumberEntity){
        itemView.apply {
            PhoneNumber.text=numbersEntity.number
            tvmode.text=numbersEntity.mode


        }
    }
}