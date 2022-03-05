package com.nalini.contactapp.database

import android.content.Context
import com.google.common.truth.Truth
import com.nalini.contactapp.local.ContactDatabase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test

class DatabaseTest {
    @MockK
    lateinit var context :Context
    @MockK
    lateinit var database :ContactDatabase
    @MockK
    @Before
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun getContactDatabase_equalityCheck_true(){
        mockkObject(ContactDatabase)
        every {
            ContactDatabase.getContactDatabase(context)
        } returns database
        val result = ContactDatabase.getContactDatabase(context)
        Truth.assertThat(result).isEqualTo(database)
    }

    @Test
    fun getContactDatabase_instanceCheck_true(){
        mockkObject(ContactDatabase)
        every {
            ContactDatabase.getContactDatabase(context)
        } returns database
        val result = ContactDatabase.getContactDatabase(context)
        Truth.assertThat(result).isInstanceOf(ContactDatabase::class.java)
    }

    @Test
    fun getContactDatabase_inNotnull_check(){
        mockkObject(ContactDatabase)
        every {
            ContactDatabase.getContactDatabase(context)
        } returns database
        val result = ContactDatabase.getContactDatabase(context)
        Truth.assertThat(result).isNotNull()
    }


}