package com.nalini.contactapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nalini.contactapp.R
import com.nalini.contactapp.ui.fragment.ContactsFragment
import com.nalini.contactapp.ui.fragment.FavoriteFragment
import com.nalini.contactapp.ui.fragment.GroupsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       bottomNavigation()
    }

    private fun bottomNavigation(){
        bottomNavigation.setOnItemSelectedListener { item ->
            var temp: Fragment? = null
            when (item.itemId) {
                R.id.menuContacts -> temp = ContactsFragment()
                R.id.menuGroups -> temp = GroupsFragment()
                R.id.menuFevorite -> temp = FavoriteFragment()
            }
            if (temp != null) {
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, temp)
                    .commit()
            }
            true
        }
    }




}