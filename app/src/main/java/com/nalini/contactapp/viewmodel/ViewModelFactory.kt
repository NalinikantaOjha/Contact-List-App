package com.nalini.contactapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nalini.contactapp.repository.ContactRepository
import javax.inject.Inject

class ViewModelFactory(val contactsRepository: ContactRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
return ContactsViewModel(contactsRepository)as T  }
}