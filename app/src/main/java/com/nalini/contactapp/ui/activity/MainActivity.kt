package com.nalini.contactapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import com.nalini.contactapp.R
import com.nalini.contactapp.ui.fragment.ContactsFragment
import com.nalini.contactapp.ui.fragment.FevoriteFragment
import com.nalini.contactapp.ui.fragment.GroupsFragment
import com.nalini.contactapp.ui.fragment.PhoneFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation()
    }
    private fun bottomNavigation(){
    bottomNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            var temp: Fragment? = null
            when (item.itemId) {
                R.id.menuContacts -> temp = ContactsFragment()
                R.id.menuPhone -> temp =PhoneFragment()
                R.id.menuGroups -> temp = GroupsFragment()
                R.id.menuFevorite->temp=FevoriteFragment()
            }
            if (temp != null) {
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, temp).commit()
            }
            true
        })
    }
}