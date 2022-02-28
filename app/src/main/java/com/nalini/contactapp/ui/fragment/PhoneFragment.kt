package com.nalini.contactapp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nalini.contactapp.R


class PhoneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // val uri = "tel:" + posted.trim()
//        val intent = Intent(Intent.ACTION_DIAL)
//       // intent.setData(Uri.parse(uri))
//        startActivity(intent)
        val it = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+ 7978635281));
        startActivity(it)
    }


}