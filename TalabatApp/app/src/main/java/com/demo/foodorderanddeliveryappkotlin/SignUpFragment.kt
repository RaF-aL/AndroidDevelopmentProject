package com.demo.foodorderanddeliveryappkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

@SuppressLint("ResourceType")
class SignUpFragment : Fragment(R.id.signUpFragement) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val signInLink = view.findViewById<TextView>(R.id.linkToSignIn)
        signInLink.setOnClickListener {
            val signInFragment = SignInFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, signInFragment)
                ?.commit()
        }

        return view
    }
}
