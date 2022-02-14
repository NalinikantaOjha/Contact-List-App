package com.nalini.contactapp.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactsDao {
    @Query("SELECT * FROM contactTable WHERE name LIKE '%' || :search || '%'")
    fun SearchData(search: String?): LiveData<List<ContactsEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addContacts(contactsEntity: ContactsEntity)
    @Query("select * from contactTable")
    fun getContacts():LiveData<List<ContactsEntity>>
    @Update
    fun update(contactsEntity: ContactsEntity)
    @Delete
    fun delete(contactsEntity: ContactsEntity)
    @Query("select * from contactTable where track='1'")
    fun getAllDeleteData() : LiveData<List<ContactsEntity>>
    @Query("select * from contactTable where favorite='1'")
    fun getFavoriteContacts() : LiveData<List<ContactsEntity>>

}