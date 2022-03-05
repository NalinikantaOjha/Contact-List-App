package com.nalini.contactapp


import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test



@RunWith(AndroidJUnit4::class)
    class DataBaseTest : TestCase() {

    private lateinit var db: ContactDatabase
    private lateinit var dao: ContactsDao

    @Before
    public override fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ContactDatabase::class.java).build()
        dao = db.getContactDao()
    }
    @After
    fun closeDb() {
        db.close()
    }
    @Test
    fun contact_Add_And_Fetch() = runBlocking {
        val contactsEntity=ContactsEntity("Nalini",
            false,false,false)
        dao.addContacts(contactsEntity)
        val languages = dao.getAllContacts2()
        val contacts=dao.getAllContacts()
        assertTrue(languages.contains(contactsEntity))

    }

}