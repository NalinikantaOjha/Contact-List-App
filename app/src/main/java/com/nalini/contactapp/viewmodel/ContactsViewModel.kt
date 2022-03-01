package com.nalini.contactapp.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalini.contactapp.local.ContactNumberRelation
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ContactsViewModel (val contactsRepository: ContactRepository): ViewModel() {



    fun Fetch(): LiveData<ArrayList<ContactNumberRelation>> {
    return contactsRepository.fetchAll()

}


    fun getContacts(): LiveData<List<ContactNumberRelation>> {
        return contactsRepository.getContact()
    }
        fun getAllContacts(): LiveData<List<ContactsEntity>> {
            return contactsRepository.getAllContact()

    }

    fun getDelete():LiveData<List<ContactsEntity>>{
        return contactsRepository.getDelete()
    }
    fun getDeleteAllNumber():LiveData<List<NumberEntity>>{
        return contactsRepository.getDeleteAllNumber()
    }
    fun getFavorite():LiveData<List<ContactsEntity>>{
        return contactsRepository.getFavorite()
    }
    fun deleteAllContact(){
        viewModelScope.launch (Dispatchers.IO) {
            contactsRepository.deleteAllContact()
        }
    }
    fun deleteAllNumber() {
        viewModelScope.launch(Dispatchers.IO) {

            contactsRepository.deleteAllNumber()
        }
    }
    fun SearchData(search:String): LiveData<List<ContactNumberRelation>> {
        return contactsRepository.SearchData(search)


    }
    fun SearchDataNumber(search:String): LiveData<List<NumberEntity>> {
        return contactsRepository.SearchDataNumber(search)


    }
    fun SearchDataNumberList(search:String): LiveData<List<NumberEntity>> {
        return contactsRepository.SearchDataNumberList(search)
    }
    fun SearchDataNumberListTest(search:String): LiveData<List<NumberEntity>> {
        return contactsRepository.SearchDataNumberListTest(search)
    }
    fun CreateNumber(numberEntity: NumberEntity){
        viewModelScope.launch  (Dispatchers.IO){
            contactsRepository.CreateNumber(numberEntity)

        }
    }

    fun CreateContact(contactsEntity: ContactsEntity){
      viewModelScope.launch  (Dispatchers.IO){
           contactsRepository.CreateContact(contactsEntity)

        }
    }
    fun CreateContactAll(contactsEntity: List<ContactsEntity>){
        viewModelScope.launch  (Dispatchers.IO){
            contactsRepository.CreateContactAll(contactsEntity)

        }
    }

    fun update(contactsEntity: ContactsEntity){
        viewModelScope.launch  (Dispatchers.IO) {
            contactsRepository.update(contactsEntity)
        }
        }
        fun updateNumber(numberEntity: NumberEntity){
            viewModelScope.launch  (Dispatchers.IO){
                contactsRepository.updateNumber(numberEntity)

            }
    }
    fun delete(contactsEntity: ContactsEntity){
        viewModelScope.launch  (Dispatchers.IO){
            contactsRepository.delete(contactsEntity)

        }
    }
    fun deleteId(id: String){
        viewModelScope.launch  (Dispatchers.IO){
            contactsRepository.deleteId(id)

        }
    }
    fun deleteNumber(numberEntity: NumberEntity){
        viewModelScope.launch  (Dispatchers.IO){
            contactsRepository.deleteNumber(numberEntity)

        }
    }

    fun getContactNumberRelation(id:String): LiveData<List<ContactNumberRelation?>> {
        return contactsRepository.getContactNumberRelation(id)
    }
    fun getContactEntity(name: String):LiveData<ContactsEntity>{
        return contactsRepository.getContactEntity(name)
    }
    fun deleteSpecificNumber(id: String,name: String){
        viewModelScope.launch  (Dispatchers.IO){
            contactsRepository.deleteSpecificNumber(id,name)

        }
    }

    }