package com.nalini.contactapp.local

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.FtsOptions.Order


@Dao
interface ContactsDao {

    @Query("SELECT * FROM contactTable WHERE name  LIKE '%' || :search || '%' ")
    fun searchData(search: String?): LiveData<List<ContactNumberRelation>>
    @Query("SELECT * FROM NumberEntity WHERE number  LIKE '%' || :search || '%' ")
    fun searchDataNumber(search: String?): LiveData<List<NumberEntity>>
    @Query("SELECT * FROM NumberEntity WHERE name  LIKE '%' || :search || '%' ")
    fun searchDataNumberList(search: String?): LiveData<List<NumberEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
suspend    fun addContacts(contactsEntity: ContactsEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend    fun addContactsAll(name:List< ContactsEntity>)
    @Query("select * from contactTable")
    fun getContacts():LiveData<List<ContactNumberRelation>>
    @Query("select * from contactTable")
    fun getAllContacts():LiveData<List<ContactsEntity>>
    @Update
   suspend fun update(contactsEntity: ContactsEntity)
    @Delete
 suspend   fun delete(contactsEntity: ContactsEntity)
    @Delete
    fun deleteNumber(numberEntity: NumberEntity)
    @Query("select * from NumberEntity where track='1'")
    fun getAllDeleteDataNumber() : LiveData<List<NumberEntity>>
    @Query("select * from contactTable where track='1'")
    fun getAllDeleteData() : LiveData<List<ContactsEntity>>
    @Query("select * from contactTable where favorite='1'")
    fun getFavoriteContacts() : LiveData<List<ContactsEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend  fun addNumber(numberEntity: NumberEntity)
    @Update
   suspend fun updateNumber(numberEntity: NumberEntity)
    @Query("delete from contactTable")
   suspend fun deleteAllDataFromContact()
    @Query("delete from NumberEntity")
   suspend fun deleteAllDataFromNumber()
    @Transaction
    @Query("SELECT * FROM NumberEntity WHERE name= :id")
     fun getContactNumberRelation(id: String): LiveData<List<ContactNumberRelation>>

}