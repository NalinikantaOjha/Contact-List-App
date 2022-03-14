package com.nalini.contactapp.ui.activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactNumberRelation
import com.nalini.contactapp.ui.adapter.EditAdapter
import com.nalini.contactapp.ui.iterface.OnEdit
import com.nalini.contactapp.viewmodel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_edit.*

@AndroidEntryPoint
class EditActivity : AppCompatActivity() , OnEdit {
private val contactsViewModel by viewModels<ContactsViewModel>()
    private var contactsList= mutableListOf<ContactNumberRelation>()
    private lateinit var editAdapter: EditAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        contactsViewModel.user.observe(this) {
            contactsList.clear()
            contactsList.addAll(it)
            setRecycle()
        }


        tvSelectAll.setOnClickListener {
           contactsList.forEach { it ->
               if (!it.contactsEntity.track) {
                   it.number.forEach {
                       it.track=true
                       contactsViewModel.updateNumber(it) }
                   it.contactsEntity.track = true
                   contactsViewModel.update(it.contactsEntity) }
               else {
                   it.contactsEntity.track = false
                   contactsViewModel.update(it.contactsEntity) }
           }
        }

            btnDelete.setOnClickListener {
                contactsViewModel.getDeleteAllNumber().observe(this) { it ->
                    it.forEach { contactsViewModel.deleteNumber(it) }
                }
                contactsViewModel.getDelete().observe(this) { it ->
                    it.forEach { contactsViewModel.delete(it) }
                }
                onBackPressed()
            }
    }

    private fun setRecycle(){
        editAdapter = EditAdapter(this,contactsList,this)
        RecycleViewDelete.adapter = editAdapter
        RecycleViewDelete.layoutManager = LinearLayoutManager(this)
    }

    override fun Delete(contactNumberRelation: ContactNumberRelation) {
        if (!contactNumberRelation.contactsEntity.track){
            contactNumberRelation.contactsEntity.track=true
            contactsViewModel.update(contactNumberRelation.contactsEntity)
            contactNumberRelation.number.forEach {
                it.apply { track=true }
                contactsViewModel.updateNumber(it) }
          }else{
            contactNumberRelation.contactsEntity.track=false
            contactsViewModel.update(contactNumberRelation.contactsEntity) }
    }
}