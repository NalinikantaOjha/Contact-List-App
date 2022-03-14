package com.nalini.contactapp.di

import android.app.Application
import android.content.Context

import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.repository.ContactRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule  {


    @Provides
    @Singleton
    fun providesUserDatabase( context: Application):ContactDatabase{
        return ContactDatabase.getContactDatabase(context)
    }
    @Provides
    @Singleton
    fun providesUserContext( context: Application): Context {
        return context
    }
    @Provides
    @Singleton
    fun providesDao(contactDatabase: ContactDatabase):ContactsDao{
        return contactDatabase.getContactDao()
    }

}