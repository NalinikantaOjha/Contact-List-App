package com.nalini.contactapp.ui.iterface

import com.nalini.contactapp.local.NumberEntity

interface OnCall {
    fun onCall(numberEntity: NumberEntity)
    fun onSms(numberEntity: NumberEntity)
}