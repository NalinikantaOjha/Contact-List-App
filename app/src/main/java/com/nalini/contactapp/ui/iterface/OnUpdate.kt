package com.nalini.contactapp.ui.iterface

import com.nalini.contactapp.local.NumberEntity

interface OnUpdate {
    fun onUpdate(numberEntity: NumberEntity, num:String)
}