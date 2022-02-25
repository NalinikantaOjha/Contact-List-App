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
import kotlinx.android.synthetic.main.activity_add_o.*
import kotlinx.android.synthetic.main.activity_add_o.etName
import kotlinx.android.synthetic.main.activity_edit_contacts.*


class AddActivityO : AppCompatActivity() {
    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    private lateinit var contactsDatabase: ContactDatabase

    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_o)
        contactsDatabase = ContactDatabase.getContactDatabase(this)
        contactsDao = contactsDatabase.getContactDao()
        val contactsRepository = ContactRepository(contactsDao, this)
        val viewModelFactory = ViewModelFactory(contactsRepository)
        contactsViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ContactsViewModel::class.java)
        val count = LinerLayoutAdd.childCount
        Log.d("nalinichild", count.toString())
        addNew()

        btnAdd.setOnClickListener {
            addNew()
            Log.d("nalinichild", LinerLayoutAdd.childCount.toString())

        }
        tvDone.setOnClickListener {

            val contactsEntity = ContactsEntity(etName.text.toString(), false, false, false)
            contactsViewModel.CreateContact(contactsEntity)
            Toast.makeText(context, "Done CLICKED", Toast.LENGTH_LONG).show()

            for (i in 6 until LinerLayoutAdd.childCount) {
                Log.d("nalinichild", LinerLayoutAdd.childCount.toString())

                val mode: EditText = LinerLayoutAdd.getChildAt(i).findViewById(R.id.AddMode)
                Log.d("nalinichild", mode.text.toString())
                val num: EditText = LinerLayoutAdd.getChildAt(i).findViewById(R.id.AddPhone)
                val numbe = NumberEntity(
                    mode.text.toString() + etName.text.toString(),
                    etName.text.toString(),
                    track = false,
                    star = false,
                    favorite = false,
                    number = num.text.toString()
                )
                contactsViewModel.CreateNumber(numbe)


            }
        }
    }

    private fun addNew() {
        val infalater = LayoutInflater.from(this).inflate(R.layout.add_number_item_layout, null)
        LinerLayoutAdd.addView(infalater, LinerLayoutAdd.childCount)
        val delete: ImageView = infalater.findViewById(R.id.ivImageView)
        val num:EditText=infalater.findViewById(R.id.AddPhone)
        delete.setOnClickListener {
            remove(infalater)
        }
        num.addTextChangedListener(object : TextWatcher {



            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    if (p0.length==1){
                        addNew()

                    }
                }
            }
        })
    }
    private fun remove(view: View) {
        LinerLayoutAdd.removeView(view)

    }
}