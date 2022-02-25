package com.nalini.contactapp.ui.viewholder

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.ui.iterface.OnUpdate
import kotlinx.android.synthetic.main.item_layout_editcontact.view.*

class EditContactViewHolder(itemView: View, val OnUpdate:OnUpdate): RecyclerView.ViewHolder(itemView) {
    fun setData(numbersEntity: NumberEntity){
        itemView.apply {
            etMode.setText(numbersEntity.mode)
            etPhone.setText(numbersEntity.number)
            etPhone.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {


                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {


                }

                override fun afterTextChanged(s: Editable) {
                    OnUpdate.onUpdate(numbersEntity,s.toString())

                }
            })

        }
    }
}