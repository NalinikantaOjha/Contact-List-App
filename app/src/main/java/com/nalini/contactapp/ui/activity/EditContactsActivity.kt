package com.nalini.contactapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_edit_contacts.*

class EditContactsActivity : AppCompatActivity() {
    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    var a=7
    lateinit var contactsDatabase: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contacts)

        contactsDatabase = ContactDatabase.getContactDatabase(this)
        contactsDao = contactsDatabase.getContactDao()
        val contactsRepository= ContactRepository(contactsDao,this)
        val viewModelFactory = ViewModelFactory(contactsRepository)
        contactsViewModel= ViewModelProviders.of(this,viewModelFactory).
        get(ContactsViewModel::class.java)
        val intent=intent
        val contactsEntity= intent.getStringExtra("nalini10")
        etName.setText(contactsEntity.toString())
        Log.d("nalinichild",LinerLayoutEdit.childCount.toString())

        contactsViewModel.SearchDataNumberList(contactsEntity.toString()).observe(this, Observer {
           etName.setText(it[0].name)

            it.forEach {
            addNew(it.mode,it.number)



}

        })

    }
    private fun addNew(m:String,number:String) {
       val infalater = LayoutInflater.from(this).inflate(R.layout.add_number_item_layout, null)

        LinerLayoutEdit.addView(infalater, LinerLayoutEdit.childCount)
        val delete:ImageView= infalater.findViewById(R.id.ivImageView)
        val mode:EditText= infalater.findViewById(R.id.AddMode)
        mode.setText(m)
        val num:EditText=infalater.findViewById(R.id.AddPhone)
        num.setText(number)
        delete.setOnClickListener {
            remove(infalater)
        }
    }
    private fun remove(view: View){
        LinerLayoutEdit.removeView(view)

    }


}