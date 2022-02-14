package com.nalini.contactapp.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactTable")
data class ContactsEntity(
    @PrimaryKey(autoGenerate = false)@ColumnInfo(name = "name")var name:String="",
    @ColumnInfo(name = "id")var id:String,

) {

}