package com.nalini.contactapp.repository

import android.content.ContentProviderOperation
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nalini.contactapp.local.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactRepository (val contactsDao: ContactsDao,val context: Context) {


    private val userLiveData= MutableLiveData<ArrayList<ContactNumberRelation>>()
    val  user:LiveData<ArrayList<ContactNumberRelation>>
        get()=userLiveData
    val projectionFields= listOf<String>(
        ContactsContract.CommonDataKinds.Phone._ID,
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone.PHOTO_URI
    ).toTypedArray()
    val listContacts: ArrayList<ContactNumberRelation> = ArrayList<ContactNumberRelation>()

    fun fetchAll(): LiveData<ArrayList<ContactNumberRelation>> {
                val cursorLoader = android.content.CursorLoader(context,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projectionFields, null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )


        val cursor: Cursor = cursorLoader.loadInBackground()
//        val cursor: Cursor = context.contentResolver.query(

//            ContactsContract.Contacts.CONTENT_URI,
//            projectionFields,
//            null,
//            null,
//            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")!!
        val contactsMap: MutableMap<String, ContactNumberRelation> = HashMap<String, ContactNumberRelation>(cursor.count)
        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
            val nameIndex =
                cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val imageIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)

            do {
                val contactId = cursor.getString(idIndex)
                var contactDisplayName = ""
                var ContactNumber = ""
                var uri=""
                if (cursor.getString(nameIndex) != null) {
                    contactDisplayName = cursor.getString(nameIndex)
                }
                if (cursor.getString(numberIndex) != null) {
                    ContactNumber = cursor.getString(numberIndex)
                }
                if (cursor.getString(imageIndex)!=null){
                    uri=cursor.getString(imageIndex)
                }
                Log.d("naliniuri",contactDisplayName.toString()+" " +uri.toString())
                val contactsEntity=ContactsEntity(
                    contactDisplayName,
                    false,
                    false,
                    false)
                var numberList= mutableListOf<NumberEntity>()
                val number=NumberEntity("Phone$contactDisplayName",
                    contactDisplayName,
                    false,
                    false,
                    false,
                ContactNumber)
                numberList.add(number)
                val contact = ContactNumberRelation(contactsEntity,numberList)
//                    contactDisplayName,
//                    contactId.toInt(),
//                    false,
//                    false,
//                    false,
//                    ContactNumber
               // )
                contactsMap[contactId] = contact
                listContacts.add(contact)
                Log.d("nalinitest", contactDisplayName)

            } while (cursor.moveToNext())
        }

        userLiveData.postValue(listContacts)
        return user




    }
    fun testGet(id:String):ContactsEntity{
      return  contactsDao.getTest(id)
    }

    fun getContact(): LiveData<List<ContactNumberRelation>> {
        return contactsDao.getContacts()
    }
    fun getAllContact(): LiveData<List<ContactsEntity>> {
        return contactsDao.getAllContacts()
    }
    fun getContac():List<ContactsEntity>{
        return contactsDao.getAllContacts2()
    }
     fun CreateContact(contactsEntity: ContactsEntity){
         CoroutineScope(Dispatchers.IO).launch {
             contactsDao.addContacts(contactsEntity)

         }
    }
    suspend fun CreateContactAll(contactsEntity: List<ContactsEntity>){
        contactsDao.addContactsAll(contactsEntity)
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