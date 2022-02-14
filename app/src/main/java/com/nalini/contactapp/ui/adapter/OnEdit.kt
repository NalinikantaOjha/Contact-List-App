package com.nalini.contactapp.ui.adapter

import android.media.AudioTrack
import com.nalini.contactapp.local.ContactsEntity

interface OnEdit {

    fun Delete(contactsEntity: ContactsEntity)
}