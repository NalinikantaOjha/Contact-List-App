package com.nalini.contactapp.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactDatabase
import com.nalini.contactapp.local.ContactNumberRelation
import com.nalini.contactapp.local.ContactsDao
import com.nalini.contactapp.local.NumberEntity
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.ui.activity.AddActivityO
import com.nalini.contactapp.ui.activity.EditActivity
import com.nalini.contactapp.ui.activity.ViewActivity
import com.nalini.contactapp.ui.adapter.ContactAdapter
import com.nalini.contactapp.ui.adapter.SearchContactAdapter
import com.nalini.contactapp.ui.iterface.onView
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_contacts.*
import java.lang.Exception


class ContactsFragment : Fragment() ,onView{
    companion object{
        var bol =false
    }

    lateinit var contactsViewModel: ContactsViewModel
    lateinit var contactsDao: ContactsDao
    lateinit var contactsDatabase: ContactDatabase
    private var contactsList= mutableListOf<ContactNumberRelation>()
    lateinit var contactAdapter: ContactAdapter
    private var searchList= mutableListOf<NumberEntity>()
    lateinit var searchAdapter:SearchContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            ivAddContacts.setOnClickListener {
                startActivity(Intent(this.context, AddActivityO::class.java))
            }
            contactsDatabase = ContactDatabase.getContactDatabase(requireContext())
            contactsDao = contactsDatabase.getContactDao()
            val contactsRepository = ContactRepository(contactsDao, requireContext())
            val viewModelFactory = ViewModelFactory(contactsRepository)
            contactsViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(ContactsViewModel::class.java)

            tvEdit.setOnClickListener {
                startActivity(Intent(this.context, EditActivity::class.java))
            }

setRecycle()

            searchView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {


                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {


                }

                override fun afterTextChanged(s: Editable) {
                    if (s.toString()==""){
                        contactsViewModel.getContacts().observe(viewLifecycleOwner) {
                            contactsList.clear()
                            contactsList.addAll(it)
                            Log.d("nalinilistsi", contactsList.size.toString())
                            searchView.setHint("Search among " +  " call yes contact")

                            RecycleViewSearch.visibility=View.INVISIBLE
                            RecycleView.visibility=View.VISIBLE
                            setRecycle()

                        }
                    }
                   try {
                       if (s.length<10) {
                           s.toString().toInt()
                           contactsViewModel.SearchDataNumber(s.toString())
                               .observe(viewLifecycleOwner,
                                   Observer {
                                       it.forEach {
                                           Log.d(
                                               "naliniaftera",
                                               s.toString() + "number = "+it.name + it.number
                                           )

                                       }
                                       searchList.clear()
                               searchList.addAll(it)
                               Log.d("naliniafter",s.toString()+"number = "+searchList.size)

                               RecycleView.visibility=View.INVISIBLE
                               RecycleViewSearch.visibility=View.VISIBLE
                               setRecycle2()

                                   })
                       }


                   }catch (e:Exception){
                       contactsViewModel.SearchData(s.toString()).observe(viewLifecycleOwner) {
                           contactsList.clear()
                           contactsList.addAll(it)
                           Log.d("naliniafter",s.toString()+"string")
                           RecycleViewSearch.visibility=View.INVISIBLE
                           RecycleView.visibility=View.VISIBLE

                           contactAdapter.notifyDataSetChanged()
                       }
                   }





                }
            })


            contactsViewModel.Fetch().observe(viewLifecycleOwner) {
                contactsViewModel.deleteAllContact()
                  contactsViewModel.deleteAllNumber()

                it.forEach {
                    contactsViewModel.CreateContact(it.contactsEntity)

                        contactsViewModel.CreateNumber(it.number[0])

                }
            }

            contactsViewModel.getContacts().observe(viewLifecycleOwner) {
                contactsList.clear()
                contactsList.addAll(it)
                Log.d("nalinilistsi", contactsList.size.toString())
                searchView.setHint("Search among " + contactsList.size + " contact")

                RecycleViewSearch.visibility=View.INVISIBLE
                RecycleView.visibility=View.VISIBLE
                setRecycle()

            }
        
    }
    fun setRecycle(){
        contactAdapter = ContactAdapter(context as Activity,contactsList,this)
        RecycleView.adapter = contactAdapter
        RecycleView.layoutManager = LinearLayoutManager(requireContext())
    }

    fun setRecycle2(){
        searchAdapter = SearchContactAdapter(context as Activity,searchList)
        RecycleViewSearch.adapter = searchAdapter
      //  Log.d("naliniadapter",searchAdapter.list.size.toString())
        RecycleViewSearch.layoutManager = LinearLayoutManager(requireContext())
    }





    override fun onView(contactNumberRelation: ContactNumberRelation) {
        val intent=Intent(this.context,ViewActivity::class.java)
       intent.putExtra("nalini",contactNumberRelation.contactsEntity)

        startActivity(intent)
    }


}


