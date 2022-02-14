package com.nalini.contactapp.ui.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.ui.activity.EditActivity
import com.nalini.contactapp.ui.adapter.ContactAdapter
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_contacts.*


class ContactsFragment : Fragment() {
    companion object{
        private const val REQUEST_CODE = 0
    }

    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    lateinit var contactsDatabase: ContactDatabase
    private var contactsList= mutableListOf<ContactsEntity>()
    lateinit var contactAdapter: ContactAdapter
    private var deleteList= mutableListOf<ContactsEntity>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val permissions = arrayOf(Manifest.permission.READ_CONTACTS)
        ActivityCompat.requestPermissions(this.requireActivity(), permissions, REQUEST_CODE)
        contactsDatabase = ContactDatabase.getContactDatabase(requireContext())
        contactsDao = contactsDatabase.getContactDao()
        val contactsRepository=ContactRepository(contactsDao,requireContext())
        val viewModelFactory = ViewModelFactory(contactsRepository)
        contactsViewModel=ViewModelProviders.of(this,viewModelFactory).
        get(ContactsViewModel::class.java)

        tvEdit.setOnClickListener {
            startActivity(Intent(this.context,EditActivity::class.java))
        }



        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {


            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {


            }
            override fun afterTextChanged(s: Editable) {
                contactsViewModel.SearchData(s.toString()).observe(viewLifecycleOwner) {
                    contactsList.clear()
                    contactsList.addAll(it)
                    contactAdapter.notifyDataSetChanged()
                }
            }
        })



        contactsViewModel.Fetch().observe(viewLifecycleOwner) {
            it.forEach {
                contactsViewModel.CreateContact(it)
               }
        }

        contactsViewModel.getContacts().observe(viewLifecycleOwner) {
            contactsList.clear()
            contactsList.addAll(it)
            setRecycle()
        }

    }
    fun setRecycle(){
        contactAdapter = ContactAdapter(context as Activity,contactsList)
        RecycleView.adapter = contactAdapter
        RecycleView.layoutManager = LinearLayoutManager(requireContext())
    }




    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            val permissions1 = arrayOf<String>(Manifest.permission.READ_CONTACTS)
            ActivityCompat.requestPermissions(this.requireActivity(), permissions1, REQUEST_CODE)
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            }
        }
    }


}


