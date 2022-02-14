package com.nalini.contactapp.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactTable")
data class ContactsEntity(
    @PrimaryKey(autoGenerate = false)@ColumnInfo(name = "name")var name:String="",
    @ColumnInfo(name = "id")var id:Int,
    @ColumnInfo(name="track")var track:Boolean,
    @ColumnInfo(name="star")var star:Boolean,
    @ColumnInfo(name="favorite")var favorite:Boolean

) {

}