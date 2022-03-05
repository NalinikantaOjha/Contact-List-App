package com.nalini.contactapp.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.nalini.contactapp.local.ContactNumberRelation
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.repository.ContactRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
//@Config(manifest = Config.NONE)

class ContactViewModelTest {

    private val userLiveData= MutableLiveData<ContactsEntity>()
    private lateinit var   user: LiveData<ContactsEntity>
    private val contact=ContactsEntity("nalini", track = false, star = false, favorite = false)
    private val number=NumberEntity("phone","nalini",false,false,false,"897")
    private val list= mutableListOf<NumberEntity>()


    private val userLiveData2 = MutableLiveData<List<ContactNumberRelation>>()
    private lateinit var user2: LiveData<List<ContactNumberRelation>>
    private val contactNumberRelation = ContactNumberRelation(contact, list)
    private var listContactNumberRelation= mutableListOf<ContactNumberRelation>()



    @MockK
    lateinit var repository: ContactRepository
    @MockK
    lateinit var  mockViewModel : ContactsViewModel
    @MockK
    lateinit var mockContact :ContactsEntity
    @MockK
    lateinit var mockNumber :NumberEntity
    lateinit var viewModel:ContactsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel=ContactsViewModel(repository)
        user=userLiveData
        user2 = userLiveData2


    }

    //Get Data test cases

    @Test
    fun is_getAllContacts_true(){
        userLiveData.postValue(contact)
        every { repository.getContactEntity("nalini")} returns user
        assertEquals(user, viewModel.getContactEntity("nalini"))
    }


    @Test
    fun getContacts_notNullCheck_true(){
        var list2= listOf<ContactNumberRelation>()
        coEvery { viewModel.getContacts().value } returns list2
        runBlocking { list2= viewModel.getContacts().value!! }
        Truth.assertThat(list2).isNotNull()
    }



    @Test
    fun is_getContact_ture() {
        list.add(number)
        listContactNumberRelation.add(contactNumberRelation)
        userLiveData2.postValue(listContactNumberRelation)
        every { repository.getContact() } returns user2
        assertEquals(user2,viewModel.getContacts())
    }


    @Test
    fun getAllContacts_isNotNull_true(){
        var list= listOf<ContactsEntity>()
        coEvery { viewModel.getAllContacts().value }returns list
        runBlocking { list = viewModel.getAllContacts().value!! }
        Truth.assertThat(list).isNotNull()
    }

    @Test
   fun getContactEntity_is_instanceCheck_true(){
        var contacts2=contact
        coEvery { viewModel.getContactEntity("nalini").value }returns contact
        runBlocking { contacts2 = viewModel.getContactEntity("nalini").value!! }
        Truth.assertThat(contacts2).isInstanceOf(ContactsEntity::class.java)
   }



    // create test
    @Test
    fun insertContact_verifyingFunctionCall(){
        coEvery { mockViewModel.CreateContact(mockContact) }just Runs
        mockViewModel.CreateContact(mockContact)
        coVerify { mockViewModel.CreateContact(mockContact) }
    }

    @Test
    fun insertContact_verifyingFunctionCall2() {
        //stub calls
        every { mockViewModel.CreateContact(mockContact) }returns Unit
        //Execute code to test
        mockViewModel.CreateContact(mockContact)
        // verify
        verify { mockViewModel.CreateContact(mockContact) }
    }

    @Test
    fun deleteContact_verifyingFunctionCall2() {
        //stub calls
        every { mockViewModel.delete(mockContact) }returns Unit
        //Execute code to test
        mockViewModel.delete(mockContact)
        // verify
        verify { mockViewModel.delete(mockContact) }
    }

    @Test
    fun updateContact_verifyingFunctionCall() {
        //stub calls
        every { mockViewModel.update(mockContact) }returns Unit
        //Execute code to test
        mockViewModel.update(mockContact)
        // verify
        verify { mockViewModel.update(mockContact) }
    }

    @Test
    fun updateNumber_verifyingFunctionCall() {
        //stub calls
        every { mockViewModel.updateNumber(mockNumber) }returns Unit
        //Execute code to test
        mockViewModel.updateNumber(mockNumber)
        // verify
        verify { mockViewModel.updateNumber(mockNumber) }
    }

    @Test
    fun deleteNumber_verifyingFunctionCall() {
        //stub calls
        every { mockViewModel.deleteNumber(mockNumber) }returns Unit
        //Execute code to test
        mockViewModel.deleteNumber(mockNumber)
        // verify
        verify { mockViewModel.deleteNumber(mockNumber) }
    }
    @Test
    fun createNumber_verifyingFunctionCall() {
        //stub calls
        every { mockViewModel.createNumber(mockNumber) }returns Unit
        //Execute code to test
        mockViewModel.createNumber(mockNumber)
        // verify
        verify { mockViewModel.createNumber(mockNumber) }
    }

}
