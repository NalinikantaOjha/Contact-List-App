package com.nalini.contactapp.viewmodel
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
class ContactViewModelTest  {


    @MockK
    lateinit var contact:ContactsEntity
    @MockK
    lateinit var repository: ContactRepository
    @MockK
    lateinit var number :NumberEntity
    private lateinit var viewModel:ContactsViewModel

    @Before
    fun setUp() {
        val list= mutableListOf<ContactNumberRelation>()
        MockKAnnotations.init(this)
coEvery {
    repository.getContact().value
}  returns   list
        viewModel=ContactsViewModel(repository)


    }

    @Test
    fun is_Fetch_NotNull_true(){
        coEvery {  repository.fetchAll()}returns Unit
        val a=viewModel.fetch()
        Truth.assertThat(a).isNotNull()
    }



    @Test
    fun getAllContacts_isNotNull_true(){
        var list= listOf<ContactsEntity>()
        coEvery { repository.getAllContact().value }returns list
        runBlocking { list = viewModel.getAllContacts().value!! }
        Truth.assertThat(list).isNotNull()
    }
//
    @Test
    fun is_getContactEntity_true(){
        val contacts2=contact
        coEvery { repository.getContactEntity("nalini").value} returns contact
        runBlocking { viewModel.getContactEntity("nalini") }
        assertEquals(contacts2, viewModel.getContactEntity("nalini").value)
    }


    @Test
   fun getContactEntity_is_instanceCheck_true(){
        var contacts2=contact
        coEvery { repository.getContactEntity("nalini").value }returns contact
        runBlocking { contacts2 = viewModel.getContactEntity("nalini").value!! }
        Truth.assertThat(contacts2).isInstanceOf(ContactsEntity::class.java)
   }
//
    @Test
    fun is_searchData_NotNull_true(){
        val listContactNumberRelation= mutableListOf<ContactNumberRelation>()
        coEvery { repository.SearchData("nalini").value}returns listContactNumberRelation
        val searchData=viewModel.searchData("nalini")
        Truth.assertThat(searchData).isNotNull()
    }
//
    @Test
    fun is_SearchData_Number_NotNull_true(){
        val number=MutableLiveData<List<NumberEntity>>()
        coEvery { repository.SearchDataNumber("8908") }returns number
        val searchNumber=viewModel.searchDataNumber("8908")
        Truth.assertThat(searchNumber).isNotNull()
    }

    @Test
    fun is_searchDataNumberListTest_NotNull_true(){
        val numberList= mutableListOf<NumberEntity>()
        coEvery { repository.SearchDataNumberList("nalini").value }returns numberList
        val nList=viewModel.SearchDataNumberList("nalini")
        Truth.assertThat(nList).isNotNull()
    }
//
    @Test
    fun insertContact_verifyingFunctionCall(){
        coEvery { repository.CreateContact(contact) }just Runs
        viewModel.CreateContact(contact)
        coVerify { repository.CreateContact(contact) }
    }
    @Test
    fun insertNumber_verifyingFunctionCall() {
        //stub calls
        coEvery { repository.CreateNumber(number) }returns Unit
        //Execute code to test
        viewModel.createNumber(number)
        // verify
       coVerify { repository.CreateNumber(number) }
    }
//
    @Test
    fun getDeleteAllNumber_verifyingFunctionCall() {
        val numberList= mutableListOf<NumberEntity>()
       coEvery {  repository.getDeleteAllNumber().value}returns numberList
        viewModel.getDeleteAllNumber()
        coVerify { repository.getDeleteAllNumber() }
    }

    @Test
    fun updateContact_verifyingFunctionCall() {
        //stub calls
        coEvery { repository.update(contact) }returns Unit
        //Execute code to test
         runBlocking {  viewModel.update(contact)}
        // verify
        coVerify { repository.update(contact) }
    }

    @Test
    fun updateNumber_verifyFunctionCall(){
        coEvery { repository.updateNumber(number) }returns Unit
        viewModel.updateNumber(number)
        coVerify { repository.updateNumber(number) }
    }
    @Test
    fun deleteContact_verifyingFunctionCall2() {
       coEvery { repository.delete(contact) }returns Unit
        viewModel.delete(contact)
        coVerify { repository.delete(contact) }
    }

    @Test
    fun deleteNumber_verifyingFunctionCall() {
        every { repository.deleteNumber(number) }returns Unit
        viewModel.deleteNumber(number)
        verify { repository.deleteNumber(number) }
    }
    @Test
    fun deleteSpecificNumber_verifyingFunctionCall() {
        coEvery { repository.deleteSpecificNumber("number","nalini") }returns Unit
        //Execute code to test
        viewModel.deleteSpecificNumber("number","nalini")
        // verify
        coVerify { repository.deleteSpecificNumber("number","nalini") }
    }

}
