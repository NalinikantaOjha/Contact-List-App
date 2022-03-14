package com.nalini.contactapp.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nalini.contactapp.R
import com.nalini.contactapp.viewmodel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add.*

@AndroidEntryPoint
class AddActivity : AppCompatActivity() {
    private val viewModel by viewModels<ContactsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)


        tvDone.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()


        }

    }
}