package com.nalini.contactapp.repository

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.nalini.contactapp.local.ContactNumberRelation
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.local.NumberEntity
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class RepositoryTest {
    @MockK
   lateinit var  context : Context
    @MockK
    lateinit var contactDAO :ContactsDao
    @MockK
    lateinit var contact :ContactsEntity
    @MockK
    lateinit var number:NumberEntity
    private lateinit var repository: ContactRepository

@Before
fun setUp(){
    MockKAnnotations.init(this)
    repository= ContactRepository(contactDAO,context)
}

   @Test
   fun getContact_is_NotNull_true(){
       val list= mutableListOf<ContactNumberRelation>()
       coEvery { contactDAO.getContacts().value }returns list
       runBlocking { val a =repository.getContact() }
       Truth.assertThat(repository.getContact()).isNotNull()
 }

    @Test
    fun getAllContacts_is_NotNull_true(){
        val list= mutableListOf<ContactsEntity>()
        coEvery { contactDAO.getAllContacts().value }returns list
        runBlocking { val a =repository.getAllContact() }
        Truth.assertThat(repository.getAllContact()).isNotNull()
    }

    @Test
    fun createContact_functionCall(){
        coEvery { contactDAO.addContacts(contact) }returns Unit
        runBlocking { repository.CreateContact(contact) }
        coVerify { contactDAO.addContacts(contact) }
    }
    @Test
    fun createNumber_functionCall(){
        coEvery { contactDAO.addNumber(number) }returns Unit
        runBlocking { repository.CreateNumber(number) }
        coVerify { contactDAO.addNumber(number) }
    }

    @Test
    fun updateContact_functionCall(){
        coEvery { contactDAO.update(contact) }returns Unit
        runBlocking { repository.update(contact) }
        coVerify { contactDAO.update(contact) }
    }
    @Test
    fun updateNumber_functionCall(){
        coEvery { contactDAO.updateNumber(number) }returns Unit
        runBlocking { repository.updateNumber(number) }
        coVerify { contactDAO.updateNumber(number) }
    }
    @Test
    fun insertContactList_verifyFunctionCall(){
        coEvery { contactDAO.addContacts(contact) }just Runs
       runBlocking {   repository.CreateContact(contact)}
        coVerify { contactDAO.addContacts(contact) }

    }

    @Test
    fun deleteAllContact_functionCall(){
        coEvery { contactDAO.deleteAllDataFromContact() }returns Unit
        runBlocking { repository.deleteAllContact() }
        coVerify { contactDAO.deleteAllDataFromContact() }
    }

    @Test
    fun deleteAllNumber_functionCall(){
        coEvery { contactDAO.deleteAllDataFromNumber() }returns Unit
        runBlocking { repository.deleteAllNumber() }
        coVerify { contactDAO.deleteAllDataFromNumber() }
    }

    @Test
    fun getDelete_isNotNull(){
        val list= mutableListOf<ContactsEntity>()
        coEvery { contactDAO.getAllDeleteData().value }returns list
        runBlocking { repository.getDelete() }
        Truth.assertThat(repository.getDelete()).isNotNull()
    }
    @Test
    fun deleteNumber_functionCall(){
        coEvery { contactDAO.deleteNumber(number) }returns Unit
        runBlocking { repository.deleteNumber(number) }
        coVerify { contactDAO.deleteNumber(number) }
    }
    @Test
    fun deleteById_functionCall(){
        coEvery { contactDAO.deleteById("nalini") }returns Unit
        runBlocking { repository.deleteId("nalini") }
        coVerify { contactDAO.deleteById("nalini") }
    }
    @Test
    fun getContactNumberRelation(){
        val list= mutableListOf<ContactNumberRelation>()
        coEvery { contactDAO.getContactNumberRelation("nalini").value }returns list
        runBlocking { repository.getContactNumberRelation("nalini") }
        coVerify { contactDAO. getContactNumberRelation("nalini")}
    }
    @Test
    fun getContact_isNotNull_true(){
        coEvery { contactDAO.getContactEntity("nl").value}returns contact
        runBlocking { repository.getContactEntity("nl") }
        Truth.assertThat(repository.getContactEntity("nl")).isNotNull()    }

    @Test
    fun getSearchData_isNotNull_true(){
        val list= mutableListOf<ContactNumberRelation>()
        coEvery { contactDAO.searchData("nalini").value }returns list
        runBlocking { repository.SearchData("nalini") }
        Truth.assertThat(repository.SearchData("nalini")).isNotNull()    }
    @Test
    fun getSearchDataNumber_isNotNull_true(){
        val list= listOf<NumberEntity>()
        coEvery { contactDAO.searchDataNumber("nalini").value }returns list
        runBlocking { repository.SearchDataNumber("nalini") }
        Truth.assertThat(repository.SearchDataNumber("nalini")).isNotNull()    }

    @Test
    fun deleteSpecificNumber_isNotNull_true() {
        coEvery { contactDAO.deleteSpecificNumber("nalini", "na") } returns Unit
        runBlocking { repository.deleteSpecificNumber("nalini", "na") }
        coVerify { (repository.deleteSpecificNumber("nalini", "na")) }
    }
    @Test
    fun getFavorite_isNotNull(){
        val list= mutableListOf<ContactsEntity>()
        coEvery { contactDAO.getFavoriteContacts().value }returns list
        runBlocking { repository.getFavorite() }
        Truth.assertThat(repository.getFavorite()).isNotNull()    }

    @Test
    fun deleteSpecificNumber_functionCall(){
        coEvery { contactDAO.deleteSpecificNumber("id","name") }returns Unit
        runBlocking { repository.deleteSpecificNumber("id","name") }
        coVerify { contactDAO.deleteSpecificNumber("id","name") }
    }
}