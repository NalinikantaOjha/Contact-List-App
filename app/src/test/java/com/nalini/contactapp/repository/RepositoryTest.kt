package com.nalini.contactapp.repository
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import org.junit.runner.RunWith
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.viewmodel.ContactsViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

import org.robolectric.annotation.Config
import java.lang.NumberFormatException

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class RepositoryTest {
    private val userLiveData= MutableLiveData<List<ContactsEntity>>()
    private lateinit var   user: LiveData<List<ContactsEntity>>

    @MockK
    lateinit var mockRepository: ContactRepository
    @MockK
   lateinit var  context : Context
    @MockK
    lateinit var contactDAO :ContactsDao
    @MockK
    lateinit var contact :ContactsEntity
    @MockK
    lateinit var number:NumberEntity
    lateinit var repository: ContactRepository

@Before
fun setUp(){
    MockKAnnotations.init(this)
    user=userLiveData
    repository= ContactRepository(contactDAO,context)
}

 @Test
 fun deleteSpecificNumber_functionCall(){
     coEvery { mockRepository.deleteSpecificNumber("id","name") }returns Unit
     runBlocking {
         mockRepository.deleteSpecificNumber("id","name")
     }
     coVerify { mockRepository.deleteSpecificNumber("id","name") }
 }

    @Test

    fun getFavorite_isNotNull(){
        val list= mutableListOf<ContactsEntity>()
        list.add(contact)
        userLiveData.postValue(list)
        coEvery { repository.getFavorite() }returns user
        runBlocking { repository.getFavorite() }
        Truth.assertThat(repository.getFavorite()).isNotNull()    }


    @Test
    fun getDelete_isNotNull(){
    val list= mutableListOf<ContactsEntity>()
    list.add(contact)
    userLiveData.postValue(list)
    coEvery { repository.getDelete() }returns user
    runBlocking { repository.getDelete() }
    Truth.assertThat(repository.getDelete()).isNotNull()
    }

    @Test
    fun getContact_isNotNull(){
        val userLiveData= MutableLiveData<ContactsEntity>()
        lateinit var   user: LiveData<ContactsEntity>
        userLiveData.postValue(contact)
        coEvery { repository.getContactEntity("nalini") }returns user
        runBlocking { repository.getDelete() }
        Truth.assertThat(repository.getDelete()).isNotNull()
    }

    @Test
    fun deleteNumber_functionCall(){
        coEvery { mockRepository.deleteNumber(number) }returns Unit
        runBlocking {
            mockRepository.deleteNumber(number) }
        coVerify { mockRepository.deleteNumber(number) }
    }

    @Test
    fun deleteAllNumber_functionCall(){
        coEvery { mockRepository.deleteAllNumber() }returns Unit
        runBlocking {
            mockRepository.deleteAllNumber() }
        coVerify { mockRepository.deleteAllNumber() }
    }

    @Test
    fun deleteAllContact_functionCall(){
        coEvery { mockRepository.deleteAllContact() }returns Unit
        runBlocking {
            mockRepository.deleteAllContact() }
        coVerify { mockRepository.deleteAllContact() }
    }

    @Test
    fun createContact_functionCall(){
        coEvery { mockRepository.CreateContact(contact) }returns Unit
        runBlocking {
            mockRepository.CreateContact(contact) }
        coVerify { mockRepository.CreateContact(contact) }
    }

    @Test
    fun createUpdate_functionCall(){
        coEvery { mockRepository.update(contact) }returns Unit
        runBlocking {
            mockRepository.update(contact) }
        coVerify { mockRepository.update(contact) }
    }





























    @Test

    fun getContactResponse_notNullCheck_true(){
        every {
            repository.testGet("r0050")
        }returns contact
        val result = repository.testGet("r0050")
        Truth.assertThat(result).isEqualTo(contact)

    }

// check repository
    @Test
    fun isRepository_isContac_true() {
        val lit= mutableListOf<ContactsEntity>()
        val conta=ContactsEntity("nalini", track = false, star = false, favorite = false)
        lit.add(conta)
        every { mockRepository.getContac()} returns lit
        val viewModel=ContactsViewModel(mockRepository)
        assertEquals(lit, viewModel.getContac())
    }

    @Test
    fun is_getContact_true(){
         val userLiveData= MutableLiveData<ContactsEntity>()
        val  user: LiveData<ContactsEntity>
        user=userLiveData
        val conta=ContactsEntity("nalini", track = false, star = false, favorite = false)
        userLiveData.postValue(conta)
        every { mockRepository.getContactEntity("nalini")} returns user
        val viewModel=ContactsViewModel(mockRepository)
        assertEquals(user,viewModel.getContactEntity("nalini"))


    }






































    @Test
    fun insertContactList_verifyFunctionCall(){
        val viewModel = mockk<ContactsViewModel>()
        val contact = mockk<ContactsEntity>()
        coEvery {
            viewModel.CreateContact(contact)
        }just Runs
        viewModel.CreateContact(contact)
        coVerify {
            viewModel.CreateContact(contact)
        }

    }



}