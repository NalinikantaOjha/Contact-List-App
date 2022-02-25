package com.nalini.contactapp.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.nalini.contactapp.R
import com.nalini.contactapp.ui.fragment.ContactsFragment
import com.nalini.contactapp.ui.fragment.FavoriteFragment
import com.nalini.contactapp.ui.fragment.GroupsFragment
import kotlinx.android.synthetic.main.activity_main.*

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