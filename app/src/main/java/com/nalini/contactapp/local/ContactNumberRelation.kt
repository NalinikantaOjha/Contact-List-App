package com.nalini.contactapp.local

import androidx.room.Embedded
import androidx.room.Relation


data class ContactNumberRelation(
    @Embedded val contactsEntity: ContactsEntity,
    @Relation(
    parentColumn = "number",
    entityColumn = "name"
)
val number: List<NumberEntity>
)
