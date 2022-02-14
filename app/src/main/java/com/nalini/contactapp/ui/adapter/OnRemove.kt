package com.nalini.contactapp.ui.adapter

import com.nalini.contactapp.local.ContactsEntity

interface OnRemove {
    fun OnRemove(contactsEntity: ContactsEntity)
}