package com.nalini.contactapp.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
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
import kotlinx.android.synthetic.main.activity_add_o.*
import kotlinx.android.synthetic.main.activity_edit_contacts.*
import kotlinx.android.synthetic.main.activity_edit_contacts.etName

class EditContactsActivity : AppCompatActivity() {
    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    var list = mutableListOf<delete>()
    var a=0
    var nameG=""
    lateinit var contactsDatabase: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contacts)
        Log.d("nalinichild",LinerLayoutEdit.childCount.toString())

        tvDoneEdit.setOnClickListener {
            list.forEach {
                contactsViewModel.deleteSpecificNumber(it.id,it.name)
            }
            if (LinerLayoutEdit.childCount>4){
                val mode: EditText = LinerLayoutEdit.getChildAt(4).findViewById(R.id.AddMode)
                val num: EditText = LinerLayoutEdit.getChildAt(4).findViewById(R.id.AddPhone)
                Log.d("nalinichildone", num.text.toString())

                val numbe = NumberEntity(
                    mode.text.toString() + etName.text.toString(),
                    etName.text.toString(),
                    track = false,
                    star = false,
                    favorite = false,
                    number = num.text.toString()
                )
                contactsViewModel.CreateNumber(numbe)
            }

            //9	PhoneAKD	AKD	0	0	0	9853208654
            for (i in 5+a until LinerLayoutEdit.childCount) {
                Log.d("nalinichild", LinerLayoutEdit.childCount.toString())

                val mode: EditText = LinerLayoutEdit.getChildAt(i).findViewById(R.id.AddMode)
                Log.d("nalinichild", mode.text.toString())
                val num: EditText = LinerLayoutEdit.getChildAt(i).findViewById(R.id.AddPhone)
                val numbe = NumberEntity(
                    mode.text.toString() + etName.text.toString(),
                    etName.text.toString(),
                    track = false,
                    star = false,
                    favorite = false,
                    number = num.text.toString()
                )
                contactsViewModel.CreateNumber(numbe)


            }
            onBackPressed()


        }

        contactsDatabase = ContactDatabase.getContactDatabase(this)
        contactsDao = contactsDatabase.getContactDao()
        val contactsRepository= ContactRepository(contactsDao,this)
        val viewModelFactory = ViewModelFactory(contactsRepository)
        contactsViewModel= ViewModelProviders.of(this,viewModelFactory).
        get(ContactsViewModel::class.java)
        val intent=intent
        val contactsEntity= intent.getStringExtra("nalini10")
        etName.setText(contactsEntity.toString())
        Log.d("nalinichildoncreate",LinerLayoutEdit.childCount.toString())

        contactsViewModel.SearchDataNumberListTest(contactsEntity.toString()).observe(this) {
            if (it.size > 0) {
                a += it.size
                nameG=it[0].name
                etName.setText(it[0].name)
                it.forEach {
                    addNew(it.mode, it.number)



                }
            }


        }
        Log.d("nalinichildloop",LinerLayoutEdit.childCount.toString())

        addNew2()
        Log.d("nalinichildlast",LinerLayoutEdit.childCount.toString())


    }
   private fun addNew(m:String,number:String) {
       val infalater = LayoutInflater.from(this).inflate(R.layout.add_number_item_layout, null)

        LinerLayoutEdit.addView(infalater, LinerLayoutEdit.childCount)
        val delete:ImageView= infalater.findViewById(R.id.ivImageView)
        val mode:EditText= infalater.findViewById(R.id.AddMode)
       var a=""
       for (i in 0 until 5){
          a= a+m.get(i)
       }
        mode.setText(a)
        val num:EditText=infalater.findViewById(R.id.AddPhone)
        num.setText(number)
        delete.setOnClickListener {
            val d=delete(number,nameG)
            list.add(d)
            remove(infalater)
        }
    }
    private fun remove(view: View){
        LinerLayoutEdit.removeView(view)

    }
    private fun addNew2() {
        Log.d("nalinichildoncrea",LinerLayoutEdit.childCount.toString())

        val infalater = LayoutInflater.from(this).inflate(R.layout.add_number_item_layout, null)

        LinerLayoutEdit.addView(infalater, LinerLayoutEdit.childCount)
        val delete: ImageView = infalater.findViewById(R.id.ivImageView)
        val num:EditText=infalater.findViewById(R.id.AddPhone)
        delete.setOnClickListener {
            remove2(infalater)
        }
        num.addTextChangedListener(object : TextWatcher {



            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    if (p0.length==1){
                        addNew2()

                    }
                }
            }
        })
    }
    private fun remove2(view: View) {
        LinerLayoutEdit.removeView(view)

    }
}