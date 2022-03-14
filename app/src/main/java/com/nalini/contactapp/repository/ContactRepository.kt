package com.nalini.contactapp.repository

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nalini.contactapp.local.ContactNumberRelation
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.local.NumberEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class  ContactRepository @Inject constructor(val contactsDao: ContactsDao,val context: Context) {
//    val contacts: LiveData<List<ContactNumberRelation>> = contactsDao.getContacts()

    val projectionFields= listOf<String>(
        ContactsContract.CommonDataKinds.Phone._ID,
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI
    ).toTypedArray()


    fun fetchAll(){
        var numberList= mutableListOf<NumberEntity>()
        var contalist= mutableListOf<ContactsEntity>()

        val cursor: Cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projectionFields,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")!!

        val contactsMap: MutableMap<String, ContactNumberRelation> = HashMap<String, ContactNumberRelation>(cursor.count)

        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
            val nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val imageIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI)

            do {

                val contactId = cursor.getString(idIndex)
                val  contactDisplayName = cursor.getString(nameIndex)
                val ContactNumber = cursor.getString(numberIndex)
                var uri=""
//                if (cursor.getString(nameIndex) != null) {
//                    contactDisplayName = cursor.getString(nameIndex)
//                }
//                if (cursor.getString(numberIndex) != null) {
//                    ContactNumber = cursor.getString(numberIndex)
//                }
                if (cursor.getString(imageIndex)!=null){
                    uri=cursor.getString(imageIndex)
                }
                val contactsEntity=ContactsEntity(contactDisplayName, false, false, false,contactId)
                val number=NumberEntity("Phone$contactId", contactId, false, false, false,
                ContactNumber)
                numberList.add(number)
                contalist.add(contactsEntity)
                val contact = ContactNumberRelation(contactsEntity,numberList)
                contactsMap[contactId] = contact
                Log.d("nalinitest", contactDisplayName)
            } while (cursor.moveToNext())
        }
        op(contalist,numberList)
    }
    fun op(list:List<ContactsEntity>,list2:List<NumberEntity>){
        CoroutineScope(Dispatchers.IO).launch {
            contactsDao.deleteAllDataFromNumber()
            contactsDao.deleteAllDataFromContact()
            contactsDao.addContactsAll(list)
            contactsDao.addNumberAll(list2)
        }
    }
    fun getContact(): LiveData<List<ContactNumberRelation>> {
        return contactsDao.getContacts()
    }
    fun getAllContact(): LiveData<List<ContactsEntity>> {
        return contactsDao.getAllContacts()
    }

     suspend fun CreateContact(contactsEntity: ContactsEntity){
             contactsDao.addContacts(contactsEntity)
    }

    suspend fun CreateNumber(number:NumberEntity){
        contactsDao.addNumber(number)
    }

   suspend fun update(contactsEntity: ContactsEntity){
           contactsDao.update(contactsEntity)
    }

   suspend fun updateNumber(number: NumberEntity){
        contactsDao.updateNumber(number)
    }

   suspend fun delete(contactsEntity: ContactsEntity){
            contactsDao.delete(contactsEntity)
    }

    suspend fun deleteId(id: String){
        contactsDao.deleteById(id)
    }

   suspend fun deleteAllContact(){
        contactsDao.deleteAllDataFromContact()
    }

   suspend fun deleteAllNumber(){
        contactsDao.deleteAllDataFromNumber()
    }

    fun deleteNumber(number: NumberEntity){
        contactsDao.deleteNumber(number)

    }

    fun getContactNumberRelation(id:String):LiveData<List<ContactNumberRelation?>>{
       return contactsDao.getContactNumberRelation(id)
    }

    fun getContactEntity(name: String):LiveData<ContactsEntity>{
        return contactsDao.getContactEntity(name)
    }

    fun SearchData(search:String):LiveData<List<ContactNumberRelation>>{
       return contactsDao.searchData(search)
    }

    fun SearchDataNumber(search:String):LiveData<List<NumberEntity>>{
        return contactsDao.searchDataNumber(search)
    }

    fun SearchDataNumberList(search:String):LiveData<List<NumberEntity>>{
        return contactsDao.searchDataNumberList(search)
    }

    fun SearchDataNumberListTest(search:String):LiveData<List<NumberEntity>>{
        return contactsDao.searchDataNumberListTest(search)
    }

    fun getDelete():LiveData<List<ContactsEntity>>{
        return contactsDao.getAllDeleteData()
    }

    fun getDeleteAllNumber():LiveData<List<NumberEntity>>{
        return contactsDao.getAllDeleteDataNumber()
    }
    fun getFavorite():LiveData<List<ContactsEntity>>{
        return contactsDao.getFavoriteContacts()
    }

    suspend fun deleteSpecificNumber(id:String,name:String){
        contactsDao.deleteSpecificNumber(id,name)
    }



}





/*
//                val cursorLoader = android.content.CursorLoader(context,
//            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//            projectionFields, null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
//        )
//
//
//        val cursor: Cursor = cursorLoader.loadInBackground()
 */