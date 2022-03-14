package com.nalini.contactapp.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.ui.adapter.NumbersAdapter
import com.nalini.contactapp.ui.iterface.OnCall
import com.nalini.contactapp.viewmodel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_view.*

@AndroidEntryPoint
class ViewActivity : AppCompatActivity(),OnCall {
    private val contactsViewModel by viewModels<ContactsViewModel>()

    private var numberlist= mutableListOf<NumberEntity>()
    lateinit var numberAdapter:NumbersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        // get intent Data
        val intent=intent
       val contactsEntity= intent.getParcelableExtra<ContactsEntity>("nalini")

        tvName.text = contactsEntity?.name.toString()
        tvEdit.setOnClickListener {
            val intent=Intent(this,EditContactsActivity::class.java)
            intent.putExtra("nalini10", contactsEntity?.number.toString())

            startActivity(intent)
        }
        tvAddToFavorites.setOnClickListener {
            contactsViewModel.getContactEntity(contactsEntity?.number.toString()).observe(this
            ) {
                it.favorite = true
                it.star = true
                contactsViewModel.update(it)
            }


        }
        ivShareVCard.setOnClickListener {
            contactsViewModel.getContactNumberRelation(contactsEntity!!.number).observe(this) {
                if (it.size > 0) {
                    val n = it[0]?.number?.get(0)?.number
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        n.toString()
                    )
                    sendIntent.type = "text/plain"
                    startActivity(sendIntent)
                }
            }

        }
        setRecycle()
Log.d("getContact",contactsEntity?.number.toString())

    contactsViewModel.getContactNumberRelation( contactsEntity?.number.toString()).observe(this) {
        if (it.size > 0) {
            numberlist.clear()
            numberlist.addAll(it[0]?.number as MutableList<NumberEntity>)
            numberAdapter.notifyDataSetChanged()
        } else {
            numberlist.clear()
            numberAdapter.notifyDataSetChanged()
        }

    }


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