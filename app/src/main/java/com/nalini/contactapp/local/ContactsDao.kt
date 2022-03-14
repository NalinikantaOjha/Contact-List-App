package com.nalini.contactapp.local

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ContactsDao {
    //Add contact

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContacts(contactsEntity: ContactsEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend    fun addContactsAll(name:List< ContactsEntity>)

    //Add Number

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun addNumber(numberEntity: NumberEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend    fun addNumberAll(name:List< NumberEntity>)
    //get Contact

    @Query("select * from contactTable")
    fun getAllContacts():LiveData<List<ContactsEntity>>
    @Query("select * from contactTable")
    fun getAllContacts2():List<ContactsEntity>
    @Query("select * from contactTable where name= :id")
    fun getTest(id:String):ContactsEntity
    @Query("SELECT * FROM contactTable WHERE number=:name")
    fun getContactEntity(name: String):LiveData<ContactsEntity>
    @Query("select * from contactTable where track='1'")
    fun getAllDeleteData() : LiveData<List<ContactsEntity>>
    @Query("select * from contactTable where favorite='1'")
    fun getFavoriteContacts() : LiveData<List<ContactsEntity>>

    // get Number

    @Query("SELECT * FROM NumberEntity WHERE number  LIKE '%' || :search || '%' ")
    fun searchDataNumber(search: String?): LiveData<List<NumberEntity>>
    @Query("SELECT * FROM NumberEntity WHERE name  LIKE '%' || :search || '%' ")
    fun searchDataNumberList(search: String?): LiveData<List<NumberEntity>>
    @Query("SELECT * FROM NumberEntity WHERE name  = :search")
    fun searchDataNumberListTest(search: String?): LiveData<List<NumberEntity>>
    @Query("select * from NumberEntity where track='1'")
    fun getAllDeleteDataNumber() : LiveData<List<NumberEntity>>

    //Returning ContactNumberRelation

    @Transaction
    @Query("select * from contactTable")
    fun getContacts():LiveData<List<ContactNumberRelation>>
    @Transaction
    @Query("SELECT * FROM contactTable WHERE number= :id")
    fun getContactNumberRelation(id: String): LiveData<List<ContactNumberRelation?>>
    @Transaction
    @Query("SELECT * FROM contactTable WHERE name  LIKE '%' || :search || '%' ")
    fun searchData(search: String?): LiveData<List<ContactNumberRelation>>

    //Delete And Update Contact
    @Delete
    suspend   fun delete(contactsEntity: ContactsEntity)

    @Update
    suspend fun update(contactsEntity: ContactsEntity)

 //Delete and Update Number

    @Delete
    fun deleteNumber(numberEntity: NumberEntity)
    @Query("delete from contactTable")
   suspend fun deleteAllDataFromContact()
    @Query("delete from NumberEntity")
   suspend fun deleteAllDataFromNumber()
    @Query("DELETE FROM NumberEntity WHERE mode = :id")
    fun deleteById(id: String)
    @Query("delete from NumberEntity where number= :id and name= :name")
    suspend fun deleteSpecificNumber(id:String,name:String)

    @Update
    suspend fun updateNumber(numberEntity: NumberEntity)
}