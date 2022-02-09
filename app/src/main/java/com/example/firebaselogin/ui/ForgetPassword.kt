package com.example.firebaselogin.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.firebaselogin.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_forget_password.*


class ForgetPassword : Fragment(R.layout.fragment_forget_password) {

    private val TAG = "ForgetPassword"
    private lateinit var navController: NavController
    private var mAuth = Firebase.auth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val emailTxt: EditText = view.findViewById(R.id.usernameforgot_ed)

        Send_btn.setOnClickListener {
            mAuth.sendPasswordResetEmail(emailTxt.text.toString()).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireActivity(), "Email Send", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.action_forgetPassword_to_loginFragment)
                } else {
                    Toast.makeText(requireActivity(), "Incorrect Email", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}