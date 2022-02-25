package com.nalini.contactapp.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class NumberEntity (
    @PrimaryKey(autoGenerate = false)     @ColumnInfo(name="mode")var mode:String,
    @ColumnInfo(name = "name")var name:String="",
    @ColumnInfo(name="track")var track:Boolean,
    @ColumnInfo(name="star")var star:Boolean,
    @ColumnInfo(name="favorite")var favorite:Boolean,
    @ColumnInfo(name="number")var number:String):Parcelable{


}