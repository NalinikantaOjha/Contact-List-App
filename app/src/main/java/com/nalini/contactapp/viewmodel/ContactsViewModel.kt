package com.nalini.contactapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ContactsViewModel(val contactsRepository: ContactRepository): ViewModel() {
    fun Fetch(): LiveData<ArrayList<ContactsEntity>> {
       return contactsRepository.fetchAll()
    }

    fun getContacts(): LiveData<List<ContactsEntity>> {
            return contactsRepository.getContact()


    }
    fun CreateContact(contactsEntity: ContactsEntity){
      viewModelScope.launch  (Dispatchers.IO){
           contactsRepository.CreateContact(contactsEntity)

        }
    }

    fun update(contactsEntity: ContactsEntity){
        viewModelScope.launch  (Dispatchers.IO){
            contactsRepository.update(contactsEntity)

        }
    }
    fun delete(contactsEntity: ContactsEntity){
        viewModelScope.launch  (Dispatchers.IO){
            contactsRepository.delete(contactsEntity)

        }
    }


}