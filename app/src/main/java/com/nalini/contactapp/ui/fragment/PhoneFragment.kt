package com.nalini.contactapp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nalini.contactapp.R


class PhoneFragment : Fragment(R.layout.fragment_phone) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // val uri = "tel:" + posted.trim()
//        val intent = Intent(Intent.ACTION_DIAL)
//       // intent.setData(Uri.parse(uri))
//        startActivity(intent)

        val it = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+ 7978635281))
        startActivity(it)
    }


}