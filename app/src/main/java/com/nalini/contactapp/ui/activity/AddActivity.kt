package com.nalini.contactapp.ui.activity

import android.content.ContentProviderOperation
import android.content.ContentProviderResult
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import com.nalini.contactapp.R
import kotlinx.android.synthetic.main.activity_add.*
import java.lang.Exception

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        tvDone.setOnClickListener {
            saveContact()
        }

    }

    private fun saveContact() {
        val name =etName.text
        val phone=etPhone.text
        var addList=ArrayList<ContentProviderOperation>()
        val id=addList.size
        addList.add(ContentProviderOperation.newInsert(
            ContactsContract.RawContacts.CONTENT_URI).withValue(ContactsContract.RawContacts.ACCOUNT_NAME,null)
            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME,null).build())


        addList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID,id)
            .withValue(ContactsContract.RawContacts.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,name.toString()).build())

        addList.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID,id)
            .withValue(ContactsContract.RawContacts.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,phone.toString()).build())
        try {
contentResolver.applyBatch(ContactsContract.AUTHORITY,addList)
        }catch (E:Exception){
            E.printStackTrace()

        }


    }
}