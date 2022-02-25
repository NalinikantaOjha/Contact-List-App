package com.nalini.contactapp.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactNumberRelation
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.ui.activity.FavoriteActivity
import com.nalini.contactapp.ui.activity.RemoveFevActivity
import com.nalini.contactapp.ui.activity.ViewActivity
import com.nalini.contactapp.ui.adapter.ContactAdapter
import com.nalini.contactapp.ui.adapter.FavoriteAdapter
import com.nalini.contactapp.ui.iterface.onView
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.fragment_fevorite.*


class FavoriteFragment : Fragment(),onView {

    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    lateinit var contactsDatabase: ContactDatabase
    private var fevoriteList= mutableListOf<ContactsEntity>()
    lateinit var contactAdapter: FavoriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fevorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsDatabase = ContactDatabase.getContactDatabase(requireContext())
        contactsDao = contactsDatabase.getContactDao()
        val contactsRepository= ContactRepository(contactsDao,requireContext())
        val viewModelFactory = ViewModelFactory(contactsRepository)
        contactsViewModel= ViewModelProviders.of(this,viewModelFactory).
        get(ContactsViewModel::class.java)
        ivFavorite.setOnClickListener {
            startActivity(Intent(this.context,FavoriteActivity::class.java))
        }
        tvEditFavorite.setOnClickListener {
            startActivity(Intent(this.context,RemoveFevActivity::class.java))
        }


        contactsViewModel.getFavorite().observe(viewLifecycleOwner, Observer {
            fevoriteList.clear()
            fevoriteList.addAll(it)
            setRecycle()
        })

    }
    fun setRecycle(){
        contactAdapter =FavoriteAdapter(context as Activity,fevoriteList,this)
        RecycleViewFavorite.adapter = contactAdapter
        RecycleViewFavorite.layoutManager = LinearLayoutManager(requireContext())
    }



    override fun onView(contactNumberRelation: ContactNumberRelation) {
    }

}