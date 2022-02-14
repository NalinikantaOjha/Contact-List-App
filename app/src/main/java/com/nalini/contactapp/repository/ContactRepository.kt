package com.nalini.contactapp.repository

import android.content.Context
import android.content.CursorLoader
import android.database.Cursor
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity

class ContactRepository(val contactsDao: ContactsDao,val context: Context) {
    private val userLiveData= MutableLiveData<ArrayList<ContactsEntity>>()

    val  user:LiveData<ArrayList<ContactsEntity>>
        get()=userLiveData
    fun fetchAll(): LiveData<ArrayList<ContactsEntity>> {
        val projectionFields= listOf<String>(
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,).toTypedArray()
        val listContacts: ArrayList<ContactsEntity> = ArrayList<ContactsEntity>()
        var cursorLoader = CursorLoader(context,
            ContactsContract.Contacts.CONTENT_URI,
            projectionFields, null,null,null
        )


        val cursor: Cursor = cursorLoader.loadInBackground()
        val contactsMap: MutableMap<String, ContactsEntity> = HashMap<String, ContactsEntity>(cursor.count)
        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
            val nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            do {
                val contactId = cursor.getString(idIndex)
                var contactDisplayName = ""
                if (cursor.getString(nameIndex) != null){
                    contactDisplayName = cursor.getString(nameIndex)
                }
                val contact = ContactsEntity(contactDisplayName,contactId.toString(),false)
                contactsMap[contactId] = contact
                listContacts.add(contact)
            } while (cursor.moveToNext())
        }
        cursor.close()
        userLiveData.postValue(listContacts)
        return user
    }

    fun getContact(): LiveData<List<ContactsEntity>> {
        return contactsDao.getContacts()
    }
    fun CreateContact(contactsEntity: ContactsEntity){
            contactsDao.addContacts(contactsEntity)


    }
    fun update(contactsEntity: ContactsEntity){
           contactsDao.update(contactsEntity)

    }
    fun delete(contactsEntity: ContactsEntity){
            contactsDao.delete(contactsEntity)

    }
    fun SearchData(search:String):LiveData<List<ContactsEntity>>{
       return contactsDao.SearchData(search)
    }


}