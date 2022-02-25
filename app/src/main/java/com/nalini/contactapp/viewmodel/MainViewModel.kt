package com.nalini.contactapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(context: Context):ViewModel() {

    private  val data:MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    init {
        data.value=false
    }

}