package com.nalini.contactapp.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.*
import com.nalini.contactapp.repository.ContactRepository
import com.nalini.contactapp.ui.activity.AddActivityO
import com.nalini.contactapp.ui.activity.EditActivity
import com.nalini.contactapp.ui.activity.ViewActivity
import com.nalini.contactapp.ui.adapter.ContactAdapter
import com.nalini.contactapp.ui.adapter.SearchContactAdapter
import com.nalini.contactapp.ui.iterface.onView
import com.nalini.contactapp.viewmodel.ContactsViewModel
import com.nalini.contactapp.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_contacts.*

@AndroidEntryPoint
class ContactsFragment : Fragment(R.layout.fragment_contacts) ,onView{
    private val contactsViewModel by viewModels<ContactsViewModel>()

    private var contactsList= mutableListOf<ContactNumberRelation>()
    lateinit var contactAdapter: ContactAdapter
    private var searchList= mutableListOf<NumberEntity>()
    lateinit var searchAdapter:SearchContactAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Progressbar.visibility = View.VISIBLE
        contactsViewModel.fetch()
        setRecycle()

        contactsViewModel.user.observe(viewLifecycleOwner) {
            contactsList.clear()
            contactsList.addAll(it)
            contactAdapter.notifyDataSetChanged()
        }
        //Go to Add Contacts Activity
        ivAddContacts.setOnClickListener {
            startActivity(Intent(this.context, AddActivityO::class.java))
        }
        //Go to Delete Activity
        tvEdit.setOnClickListener {
            startActivity(Intent(this.context, EditActivity::class.java))
        }
        //On Search
               searchView.addTextChangedListener(object : TextWatcher { override fun beforeTextChanged(s: CharSequence,start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable) {
                        if (s.toString()=="") RecycleView.adapter=contactAdapter
                        try {
                            if (s.length<10) {
                                s.toString().toInt()
                                contactsViewModel.searchDataNumber(s.toString()).observe(viewLifecycleOwner) {
                                        searchList.clear()
                                        searchList.addAll(it)
                                        searchAdapter = SearchContactAdapter(context as Activity, searchList)
                                        RecycleView.adapter = searchAdapter
                                        searchAdapter.notifyDataSetChanged()
                                }
                            }
                        }catch (e:Exception){
                                    contactsViewModel.searchData(s.toString())
                                    contactsViewModel.user.observe(viewLifecycleOwner) {
                                    contactsList.clear()
                                    contactsList.addAll(it)
                                    contactAdapter.notifyDataSetChanged()
                                }
                        }
                    }
                })
            }
  // function to set RecycleView
    private fun setRecycle(){
        Progressbar.visibility=View.GONE
        contactAdapter = ContactAdapter(requireContext(),contactsList,this)
        RecycleView.adapter = contactAdapter
        RecycleView.layoutManager = LinearLayoutManager(requireContext())
    }
   //to Go to view Activity
    override fun onView(contactNumberRelation: ContactsEntity) {
        val intent=Intent(this.context,ViewActivity::class.java)
       intent.putExtra("nalini",contactNumberRelation)
        startActivity(intent)
    }
}


