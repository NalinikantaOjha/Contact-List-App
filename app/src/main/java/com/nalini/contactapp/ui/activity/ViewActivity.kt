package com.nalini.contactapp.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.*
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.ui.adapter.FavoriteAdapter
import com.nalini.contactapp.ui.adapter.NumbersAdapter
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_view.*
import kotlinx.android.synthetic.main.fragment_fevorite.*

class ViewActivity : AppCompatActivity() {
    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    lateinit var contactsDatabase: ContactDatabase
    private var numberlist= mutableListOf<NumberEntity>()
    lateinit var numberAdapter:NumbersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        val intent=intent
       val contactsEntity= intent.getParcelableExtra<ContactsEntity>("nalini")
        contactsDatabase = ContactDatabase.getContactDatabase(this)
        contactsDao = contactsDatabase.getContactDao()
        val contactsRepository = ContactRepository(contactsDao, this)
        val viewModelFactory = ViewModelFactory(contactsRepository)
        contactsViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ContactsViewModel::class.java)

        tvName.text = contactsEntity?.name.toString()
        tvEdit.setOnClickListener {
            val intent=Intent(this,EditContactsActivity::class.java)
            intent.putExtra("nalini10", contactsEntity?.name.toString())

            startActivity(intent)
        }

        contactsViewModel.getContactNumberRelation(contactsEntity!!.name).observe(this, Observer {
         numberlist= it[0].number as MutableList<NumberEntity>
            setRecycle()
           // PhoneNumber.text=it[0].number[0].number.toString()
          //  Log.d("naliniwork",it[0].number[0].number.toString())

        })



    }
    fun setRecycle(){
        numberAdapter = NumbersAdapter(this,numberlist)
       RecycleViewNumbers.adapter = numberAdapter
        RecycleViewNumbers.layoutManager = LinearLayoutManager(this)
    }

}