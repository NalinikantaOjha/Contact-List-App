package com.nalini.contactapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.*
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.ui.adapter.EditAdapter
import com.nalini.contactapp.ui.iterface.OnEdit
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.fragment_contacts.*

class EditActivity : AppCompatActivity() , OnEdit {
    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    lateinit var contactsDatabase: ContactDatabase
    private var contactsList= mutableListOf<ContactNumberRelation>()
    lateinit var editAdapter: EditAdapter


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
            search_text.hint = "Search among " + contactsList.size + " contact"

            setRecycle()

        }
        tvSelectAll.setOnClickListener {

           contactsList.forEach {
               if (!it.contactsEntity.track) {
                   it.number.forEach {
                       it.track=true
                       contactsViewModel.updateNumber(it)
                   }
                   it.contactsEntity.track = true
                   contactsViewModel.update(it.contactsEntity)
               }
               else {
                   it.contactsEntity.track = false
                   contactsViewModel.update(it.contactsEntity)

               }
           }
        }

            btnDelete.setOnClickListener {
                contactsViewModel.getDeleteAllNumber().observe(this, Observer {
                    it.forEach {
                        contactsViewModel.deleteNumber(it)
                    }
                })
                contactsViewModel.getDelete().observe(this, Observer {
                    it.forEach {
                 contactsViewModel.delete(it)
}
                })



                onBackPressed()

            }




    }
    fun setRecycle(){
        editAdapter = EditAdapter(this,contactsList,this)
        RecycleViewDelete.adapter = editAdapter
        RecycleViewDelete.layoutManager = LinearLayoutManager(this)
    }







    override fun Delete(contactNumberRelation: ContactNumberRelation) {
        if (!contactNumberRelation.contactsEntity.track){
            contactNumberRelation.contactsEntity.track=true
              // val contact=ContactsEntity(contactNumberRelation.contactsEntity.name,contactNumberRelation.contactsEntity.id,true,contactNumberRelation.contactsEntity.star,contactNumberRelation.contactsEntity.favorite)
            contactsViewModel.update(contactNumberRelation.contactsEntity)
            contactNumberRelation.number.forEach {
                it.apply {
                    track=true
                }
                contactsViewModel.updateNumber(it)


            }
        }else{
            contactNumberRelation.contactsEntity.track=false
            contactsViewModel.update(contactNumberRelation.contactsEntity)
            // val contact=ContactsEntity(contactNumberRelation.contactsEntity.name,contactNumberRelation.contactsEntity.id,false,contactNumberRelation.contactsEntity.star,contactNumberRelation.contactsEntity.favorite)
        }
    }
}