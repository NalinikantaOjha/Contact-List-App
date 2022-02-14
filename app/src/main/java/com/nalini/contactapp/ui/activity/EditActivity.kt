package com.nalini.contactapp.ui.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.ui.adapter.ContactAdapter
import com.nalini.contactapp.ui.adapter.EditAdapter
import com.nalini.contactapp.ui.adapter.OnEdit
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.fragment_contacts.*

class EditActivity : AppCompatActivity() ,OnEdit{
    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    lateinit var contactsDatabase: ContactDatabase
    private var contactsList= mutableListOf<ContactsEntity>()
    lateinit var editAdapter: EditAdapter
    private var deleteList= mutableListOf<ContactsEntity>()
    private var deleteIntList= mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        contactsDatabase = ContactDatabase.getContactDatabase(this)
        contactsDao = contactsDatabase.getContactDao()
        val contactsRepository= ContactRepository(contactsDao,this)
        val viewModelFactory = ViewModelFactory(contactsRepository)
        contactsViewModel= ViewModelProviders.of(this,viewModelFactory).
        get(ContactsViewModel::class.java)
        contactsViewModel.getContacts().observe(this) {
            contactsList.clear()
            contactsList.addAll(it)
            setRecycle()

        }
        tvSelectAll.setOnClickListener {
           contactsList.forEach {
               if (it.track==false){
                   val contact=ContactsEntity(it.name,it.id,true,it.star,it.favorite)
                   contactsViewModel.update(contact)
                   deleteList.clear()
                   deleteList.add(it)
               }
               else {
                   val contact=ContactsEntity(it.name,it.id,false,it.star,it.favorite)
                   contactsViewModel.update(contact)
                   deleteList.clear()
               }
           }
        }
        contactsViewModel.getDelete().observe(this, Observer {
            deleteList.addAll(it)
            Log.d("anlinidatadelete",it.size.toString())
            btnDelete.setOnClickListener {
                deleteList.forEach {
                    contactsViewModel.delete(it)

                }




            }

        })


    }
    fun setRecycle(){
        editAdapter = EditAdapter(this,contactsList,this)
        RecycleViewDelete.adapter = editAdapter
        RecycleViewDelete.layoutManager = LinearLayoutManager(this)
    }



    override fun Delete(contactsEntity: ContactsEntity) {
        if (contactsEntity.track==false){
            val contact=ContactsEntity(contactsEntity.name,contactsEntity.id,true,contactsEntity.star,contactsEntity.favorite)
            contactsViewModel.update(contact)
        }else{
            val contact=ContactsEntity(contactsEntity.name,contactsEntity.id,false,contactsEntity.star,contactsEntity.favorite)
           contactsViewModel.update(contact)
        }

    }
}