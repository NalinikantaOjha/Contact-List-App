package com.nalini.contactapp.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.ui.iterface.OnCall
import kotlinx.android.synthetic.main.numbers_view_item_layout.view.*

class NumbersViewHolder(itemView: View,val onCall:OnCall) :RecyclerView.ViewHolder(itemView) {
    fun setData(numbersEntity: NumberEntity){
        itemView.apply {
            PhoneNumber.text=numbersEntity.number
            tvmode.text=numbersEntity.mode
            ivCall.setOnClickListener {
                onCall.onCall(numbersEntity)
            }
            ivMessage.setOnClickListener {
                onCall.onSms(numbersEntity)
            }


        }
    }
}