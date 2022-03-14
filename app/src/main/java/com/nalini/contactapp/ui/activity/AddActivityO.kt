package com.nalini.contactapp.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_o.*
import kotlinx.android.synthetic.main.activity_add_o.etName

@AndroidEntryPoint
class AddActivityO : AppCompatActivity() {
private val contactsViewModel by viewModels<ContactsViewModel>()

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_o)
        val count = LinerLayoutAdd.childCount
        addNew()

        tvDone.setOnClickListener {
            val rnds = (999999..100000000).random()
            val contactsEntity = ContactsEntity(etName.text.toString(), false, false, false,rnds.toString())
            contactsViewModel.CreateContact(contactsEntity)

            for (i in 5 until LinerLayoutAdd.childCount-1) {
                val mode: EditText = LinerLayoutAdd.getChildAt(i).findViewById(R.id.AddMode)
                val num: EditText = LinerLayoutAdd.getChildAt(i).findViewById(R.id.AddPhone)
                val numbe = NumberEntity(mode.text.toString() + rnds.toString(), rnds.toString(), track = false, star = false, favorite = false, number = num.text.toString())
                contactsViewModel.createNumber(numbe) }
           }
       }

    private fun addNew() {
        val infalater = LayoutInflater.from(this).inflate(R.layout.add_number_item_layout, null)
        LinerLayoutAdd.addView(infalater, LinerLayoutAdd.childCount)
        val delete: ImageView = infalater.findViewById(R.id.ivImageView)
        val num:EditText=infalater.findViewById(R.id.AddPhone)
        delete.setOnClickListener { remove(infalater) }
        num.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    if (p0.length==1){ addNew()
                    }
                }
            }
        })
    }

    private fun remove(view: View) {
        LinerLayoutAdd.removeView(view) }
}