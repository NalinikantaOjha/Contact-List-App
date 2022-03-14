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
import androidx.core.content.ContextCompat
import com.nalini.contactapp.R

class MainActivity2 : AppCompatActivity() {
    private val REQUEST_CODE = 0
    private var check=false
    val context=this
//    lateinit var contactsViewModel: ContactsViewModel
//    lateinit var contactsDao: ContactsDao
//    lateinit var contactsDatabase: ContactDatabase
    val permissions =
        arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


//        contactsDatabase = ContactDatabase.getContactDatabase(this)
//        contactsDao = contactsDatabase.getContactDao()
//        val contactsRepository = ContactRepository(contactsDao,this)
//        val viewModelFactory = ViewModelFactory(contactsRepository)
//        contactsViewModel =
//            ViewModelProviders.of(this, viewModelFactory).get(ContactsViewModel::class.java)


}


    override fun onStart() {
        super.onStart()
        if ( ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            startActivity(Intent(this,MainActivity::class.java))

        }else{
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            showToast("Both the permissions granted")
            check=true
            startActivity(Intent(this,MainActivity::class.java))

        } else if (grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity2,
                    permissions[0]!!)) {
                val permissions1 = arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS)
                ActivityCompat.requestPermissions(this@MainActivity2, permissions1, REQUEST_CODE)

            } else {
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", "com.nalini.contactapp", null)
                )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                showToast("You are mandate to give both the permission")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}