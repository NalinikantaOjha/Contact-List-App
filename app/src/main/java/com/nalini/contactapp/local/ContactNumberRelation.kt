package com.nalini.contactapp.local

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize


class ContactNumberRelation
    (
    @Embedded val contactsEntity: ContactsEntity, @Relation(
    parentColumn = "name",
    entityColumn = "name"
)
val number: List<NumberEntity>
)