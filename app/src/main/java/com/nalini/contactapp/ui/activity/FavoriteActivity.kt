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
import com.nalini.contactapp.ui.adapter.AddToFevAdapter
import com.nalini.contactapp.ui.iterface.OnFavorite
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() , OnFavorite {
    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    private lateinit var contactsDatabase: ContactDatabase
    private var contactsList= mutableListOf<ContactsEntity>()
    private lateinit var addToFevAdapter:AddToFevAdapter
    private var addToFavList= mutableListOf<ContactsEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        contactsDatabase = ContactDatabase.getContactDatabase(this)
        contactsDao = contactsDatabase.getContactDao()
        val contactsRepository= ContactRepository(contactsDao,this)
        val viewModelFactory = ViewModelFactory(contactsRepository)
        contactsViewModel= ViewModelProviders.of(this,viewModelFactory).
        get(ContactsViewModel::class.java)
        contactsViewModel.getAllContacts().observe(this) {
            contactsList.clear()
            contactsList.addAll(it)
            setRecycle()

        }
        btnAddToFev.setOnClickListener {
            addToFavList.forEach {
                it.favorite=true
                contactsViewModel.update(it)
            }
            onBackPressed()

        }
    }
    private fun setRecycle(){
        addToFevAdapter = AddToFevAdapter(this,contactsList,this)
        RecycleViewAddToFev.adapter = addToFevAdapter
        RecycleViewAddToFev.layoutManager = LinearLayoutManager(this)
    }

    override fun OnFavorite(contactsEntity: ContactsEntity) {
        if (!contactsEntity.star){
            contactsEntity.star=true
            contactsViewModel.update(contactsEntity)
            addToFavList.add(contactsEntity)
        }else{
            contactsEntity.star=false
            contactsViewModel.update(contactsEntity)
        }
    }
}