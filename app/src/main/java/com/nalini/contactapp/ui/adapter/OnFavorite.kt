package com.nalini.contactapp.ui.adapter

import com.nalini.contactapp.local.ContactsEntity

interface OnFavorite {
    fun OnFavorite(contactsEntity: ContactsEntity)
}