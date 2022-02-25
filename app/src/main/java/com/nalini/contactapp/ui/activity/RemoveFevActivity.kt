package com.nalini.contactapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.ui.adapter.RemoveAdapter
import com.nalini.contactapp.ui.iterface.OnRemove
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_remove_fev.*

class RemoveFevActivity : AppCompatActivity() , OnRemove {
    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    private lateinit var contactsDatabase: ContactDatabase
    private var removeList= mutableListOf<ContactsEntity>()
    private lateinit var removeAdapter: RemoveAdapter
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



        contactsViewModel.getFavorite().observe(this) {
            removeList.clear()
            removeList.addAll(it)
            setRecycle()
        }

        btnRemove.setOnClickListener {
            remove.forEach {
                it.favorite=false
                contactsViewModel.update(it)
            }
            onBackPressed()
        }

    }
    private fun setRecycle(){
        removeAdapter =RemoveAdapter(this,removeList,this)
        RecycleViewRemove.adapter = removeAdapter
        RecycleViewRemove.layoutManager = LinearLayoutManager(this)
    }

    override fun OnRemove(contactsEntity: ContactsEntity) {
        if (contactsEntity.star){
            contactsEntity.star=false
            contactsViewModel.update(contactsEntity)
            remove.add(contactsEntity)
        }else{
            contactsEntity.star=true
            contactsViewModel.update(contactsEntity)
        }
    }

}