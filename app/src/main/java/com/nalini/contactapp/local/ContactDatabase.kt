package com.nalini.contactapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactsEntity::class,NumberEntity::class],version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun getContactDao():ContactsDao
    companion object{
        private var instance:ContactDatabase?=null
        fun getContactDatabase(context: Context):ContactDatabase{
            if (instance!=null){
                return instance!!
            }else{
                val builder= Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contactDb"
                )
                builder.fallbackToDestructiveMigration()
                instance=builder.build()
            }
            return instance!!
        }
    }
}