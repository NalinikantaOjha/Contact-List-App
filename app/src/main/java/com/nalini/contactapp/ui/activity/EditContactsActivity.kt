package com.nalini.contactapp.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.local.delete
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_edit_contacts.*
import kotlinx.android.synthetic.main.activity_edit_contacts.etName
@AndroidEntryPoint
class EditContactsActivity : AppCompatActivity() {
    private val contactsViewModel by viewModels<ContactsViewModel>()
    var list = mutableListOf<delete>()
    var a=0
    var nameG=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contacts)
        Log.d("nalinichild",LinerLayoutEdit.childCount.toString())

        tvDoneEdit.setOnClickListener {
            list.forEach { contactsViewModel.deleteSpecificNumber(it.id,it.name) }
            if (LinerLayoutEdit.childCount>4){
                val mode: EditText = LinerLayoutEdit.getChildAt(4).findViewById(R.id.AddMode)
                val num: EditText = LinerLayoutEdit.getChildAt(4).findViewById(R.id.AddPhone)
                val numbe = NumberEntity(mode.text.toString() + etName.text.toString(), etName.text.toString(), track = false, star = false, favorite = false, number = num.text.toString())
                contactsViewModel.createNumber(numbe) }

            for (i in 5+a until LinerLayoutEdit.childCount-1) {
                val mode: EditText = LinerLayoutEdit.getChildAt(i).findViewById(R.id.AddMode)
                val num: EditText = LinerLayoutEdit.getChildAt(i).findViewById(R.id.AddPhone)
                val numbe = NumberEntity(mode.text.toString() + etName.text.toString(), etName.text.toString(), track = false, star = false, favorite = false, number = num.text.toString())
                contactsViewModel.createNumber(numbe) }
                onBackPressed()
        }

        val intent=intent
        val contactsEntity= intent.getStringExtra("nalini10")
        Log.d("nalinichildoncreate",LinerLayoutEdit.childCount.toString())
        contactsViewModel.getContactEntity(contactsEntity.toString()).observe(this, Observer {
          if (it!=null){ etName.setText(it.name) } })

        ivDeleteContact.setOnClickListener {
            contactsViewModel.getContactEntity(contactsEntity.toString()).observe(this, Observer {
            if(it!=null){ contactsViewModel.delete(it) } })
            onBackPressed() }

            contactsViewModel.searchDataNumberListTest(contactsEntity.toString()).observe(this) {
            if (it.size > 0) { a += it.size
                nameG=it[0].name
                it.forEach { addNew(it.mode, it.number) }
            }
        }
        addNew2()
    }
   private fun addNew(m:String,number:String) {
       val infalater = LayoutInflater.from(this).inflate(R.layout.add_number_item_layout, null)
        LinerLayoutEdit.addView(infalater, LinerLayoutEdit.childCount)
        val delete:ImageView= infalater.findViewById(R.id.ivImageView)
        val mode:EditText= infalater.findViewById(R.id.AddMode)
       var a=""
       for (i in 0 until 5){ a= a+m.get(i) }
        mode.setText(a)
        val num:EditText=infalater.findViewById(R.id.AddPhone)
        num.setText(number)

        delete.setOnClickListener {
            val d=delete(number,nameG)
            list.add(d)
            remove(infalater) }
    }

    private fun remove(view: View){
        LinerLayoutEdit.removeView(view)
    }

    private fun addNew2() {
        val infalater = LayoutInflater.from(this).inflate(R.layout.add_number_item_layout, null)
        LinerLayoutEdit.addView(infalater, LinerLayoutEdit.childCount)
        val delete: ImageView = infalater.findViewById(R.id.ivImageView)
        val num:EditText=infalater.findViewById(R.id.AddPhone)
        delete.setOnClickListener { remove2(infalater) }
        num.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    if (p0.length==1){ addNew2() }
                }
            }
        })
    }

    private fun remove2(view: View) {
        LinerLayoutEdit.removeView(view)
    }

}