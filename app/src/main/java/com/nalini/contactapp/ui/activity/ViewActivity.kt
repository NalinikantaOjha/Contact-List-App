package com.nalini.contactapp.ui.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.*
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.ui.adapter.FavoriteAdapter
import com.nalini.contactapp.ui.adapter.NumbersAdapter
import com.nalini.contactapp.ui.iterface.OnCall
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_view.*
import kotlinx.android.synthetic.main.fragment_fevorite.*
import java.lang.Exception

class ViewActivity : AppCompatActivity(),OnCall {
    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    lateinit var contactsDatabase: ContactDatabase
    private var numberlist= mutableListOf<NumberEntity>()
    lateinit var numberAdapter:NumbersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        val intent=intent
       val contactsEntity= intent.getParcelableExtra<ContactsEntity>("nalini")
        contactsDatabase = ContactDatabase.getContactDatabase(this)
        contactsDao = contactsDatabase.getContactDao()
        val contactsRepository = ContactRepository(contactsDao, this)
        val viewModelFactory = ViewModelFactory(contactsRepository)
        contactsViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ContactsViewModel::class.java)

        tvName.text = contactsEntity?.name.toString()
        tvEdit.setOnClickListener {
            val intent=Intent(this,EditContactsActivity::class.java)
            intent.putExtra("nalini10", contactsEntity?.name.toString())

            startActivity(intent)
        }
        tvAddToFavorites.setOnClickListener {
            contactsViewModel.getContactEntity(contactsEntity?.name.toString()).observe(this,
                Observer {
                    it.favorite=true
                    it.star=true
                    contactsViewModel.update(it)
                })


        }
        ivShareVCard.setOnClickListener {
            contactsViewModel.getContactNumberRelation(contactsEntity!!.name).observe(this, Observer {
                if(it.size>0) {
                   val n= it[0]?.number?.get(0)?.number
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        n.toString()
                    )
                    sendIntent.type = "text/plain"
                    startActivity(sendIntent)
                }
                } )

        }
        setRecycle()

    contactsViewModel.getContactNumberRelation(contactsEntity!!.name).observe(this, Observer {
if(it.size>0){
    numberlist.clear()
    numberlist.addAll(it[0]?.number as MutableList<NumberEntity>)
    numberAdapter.notifyDataSetChanged()
}else{
    numberlist.clear()
    numberAdapter.notifyDataSetChanged()
}

    })





    }
    fun setRecycle(){
        numberAdapter = NumbersAdapter(this,numberlist,this)
       RecycleViewNumbers.adapter = numberAdapter
        RecycleViewNumbers.layoutManager = LinearLayoutManager(this)
    }

    override fun onCall(numberEntity: NumberEntity) {
         val uri = "tel:" + numberEntity.number.trim()
        val intent = Intent(Intent.ACTION_DIAL)
         intent.setData(Uri.parse(uri))
        startActivity(intent)
    }

    override fun onSms(numberEntity: NumberEntity) {
        val it = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+ numberEntity.number))
        startActivity(it)

    }

}