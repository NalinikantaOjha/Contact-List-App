package com.nalini.contactapp.ui.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory


class ContactsFragment : Fragment() {
    companion object{
        private const val REQUEST_CODE = 0
    }

    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    lateinit var contactsDatabase: ContactDatabase
    private var List=ArrayList<ContactsEntity>()


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

        contactsViewModel.getContacts().observe(viewLifecycleOwner, {})
        contactsViewModel.Fetch().observe(viewLifecycleOwner) {
            it.forEach {
                contactsViewModel.CreateContact(it)
            }

        }

        List.forEach {
            Log.d("anlinidata",it.name)
        }


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


