package com.nalini.contactapp.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nalini.contactapp.R
import com.nalini.contactapp.local.ContactsEntity
import com.nalini.contactapp.ui.activity.FavoriteActivity
import com.nalini.contactapp.ui.activity.RemoveFevActivity
import com.nalini.contactapp.ui.adapter.FavoriteAdapter
import com.nalini.contactapp.ui.iterface.onView
import com.nalini.contactapp.viewmodel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fevorite.*

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_fevorite),onView {
private val contactsViewModel by viewModels<ContactsViewModel>()
    private var favoriteList= mutableListOf<ContactsEntity>()
    private lateinit var contactAdapter: FavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivFavorite.setOnClickListener {
            startActivity(Intent(this.context,FavoriteActivity::class.java))
        }
        tvEditFavorite.setOnClickListener {
            startActivity(Intent(this.context,RemoveFevActivity::class.java))
        }

        contactsViewModel.getFavorite().observe(viewLifecycleOwner) {
            favoriteList.clear()
            favoriteList.addAll(it)
            setRecycle()
        }
    }
    private fun setRecycle(){
        contactAdapter =FavoriteAdapter(context as Activity,favoriteList,this)
        RecycleViewFavorite.adapter = contactAdapter
        RecycleViewFavorite.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onView(contactNumberRelation: ContactsEntity) {
    }

}