package com.nalini.contactapp.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.ui.adapter.FavoriteAdapter
import com.nalini.contactapp.ui.adapter.OnRemove
import com.nalini.contactapp.ui.adapter.RemoveAdapter
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_remove_fev.*
import kotlinx.android.synthetic.main.fragment_fevorite.*

class RemoveFevActivity : AppCompatActivity() ,OnRemove{
    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    lateinit var contactsDatabase: ContactDatabase
    private var removeList= mutableListOf<ContactsEntity>()
    lateinit var removeAdapter: RemoveAdapter
    private var remove= mutableListOf<ContactsEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_fev)
        contactsDatabase = ContactDatabase.getContactDatabase(this)
        contactsDao = contactsDatabase.getContactDao()
        val contactsRepository= ContactRepository(contactsDao,this)
        val viewModelFactory = ViewModelFactory(contactsRepository)
        contactsViewModel= ViewModelProviders.of(this,viewModelFactory).
        get(ContactsViewModel::class.java)



        contactsViewModel.getFavorite().observe(this, Observer {
           removeList.clear()
            removeList.addAll(it)
            setRecycle()
        })

        btnRemove.setOnClickListener {
            remove.forEach {
                val con=ContactsEntity(it.name,it.id,it.track,it.star,false)
                contactsViewModel.update(con)
            }
            onBackPressed()
        }

    }
    fun setRecycle(){
        removeAdapter =RemoveAdapter(this,removeList,this)
        RecycleViewRemove.adapter = removeAdapter
        RecycleViewRemove.layoutManager = LinearLayoutManager(this)
    }

    override fun OnRemove(contactsEntity: ContactsEntity) {
        if (contactsEntity.star==true){
            val contact=ContactsEntity(contactsEntity.name,contactsEntity.id,contactsEntity.track,false,contactsEntity.favorite)
            contactsViewModel.update(contact)
            remove.add(contact)
        }else{
            val contact=ContactsEntity(contactsEntity.name,contactsEntity.id,contactsEntity.track,true,contactsEntity.favorite)
            contactsViewModel.update(contact)
        }
    }

}