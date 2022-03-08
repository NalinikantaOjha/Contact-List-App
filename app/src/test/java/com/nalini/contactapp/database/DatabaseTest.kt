package com.nalini.contactapp.database

import android.content.Context
import androidx.room.Room
import com.google.common.truth.Truth
import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkObject
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DatabaseTest {
    private lateinit var db: ContactDatabase
    private lateinit var dao: ContactsDao
    @MockK
    lateinit var context :Context

    @MockK
    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        db = Room.inMemoryDatabaseBuilder(context, ContactDatabase::class.java).build()
        dao = db.getContactDao()
    }

    @Test
    fun getContactDatabase_equalityCheck_true(){
        every { ContactDatabase.getContactDatabase(context) } returns db
        val result = ContactDatabase.getContactDatabase(context)
        Truth.assertThat(result).isEqualTo(db)
    }

    @Test
    fun getContactDatabase_instanceCheck_true(){
        every { ContactDatabase.getContactDatabase(context) } returns db
        val result = ContactDatabase.getContactDatabase(context)
        Truth.assertThat(result).isInstanceOf(ContactDatabase::class.java)
    }

    @Test
    fun getContactDatabase_inNotnull_check(){
        every { ContactDatabase.getContactDatabase(context) } returns db
        val result = ContactDatabase.getContactDatabase(context)
        Truth.assertThat(result).isNotNull()
    }


}