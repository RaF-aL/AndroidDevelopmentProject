package com.demo.foodorderanddeliveryappkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

@SuppressLint("ResourceType")
class SignInFragment : Fragment(R.id.signInFragement) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val signUpLink = view.findViewById<TextView>(R.id.linkToSignUp)
        signUpLink.setOnClickListener {
            val signUpFragment = SignUpFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, signUpFragment)
                ?.commit()
        }

        return view
    }
}
