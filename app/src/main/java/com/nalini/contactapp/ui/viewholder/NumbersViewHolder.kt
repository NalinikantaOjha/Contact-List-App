package com.nalini.contactapp.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.ui.iterface.OnCall
import kotlinx.android.synthetic.main.numbers_view_item_layout.view.*

class NumbersViewHolder(itemView: View, private val onCall:OnCall) :RecyclerView.ViewHolder(itemView) {
    fun setData(numbersEntity: NumberEntity){
        itemView.apply {
            PhoneNumber.text=numbersEntity.number
            var a=""
            for (i in 0 until 5){
                a += numbersEntity.mode[i]
            }
            tvmode.text=a
            ivCall.setOnClickListener {
                onCall.onCall(numbersEntity)
            }
            ivMessage.setOnClickListener {
                onCall.onSms(numbersEntity)
            }


        }
    }
}