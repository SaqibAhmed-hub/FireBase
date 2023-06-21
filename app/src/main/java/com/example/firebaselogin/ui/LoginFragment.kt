package com.example.firebaselogin.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.firebaselogin.DashboardActivity
import com.example.firebaselogin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var mAuth: FirebaseAuth
    private val TAG = "LoginFragment"
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = Firebase.auth
        val currentuser = mAuth.currentUser
        if (currentuser != null) {
            startActivity(Intent(requireActivity(), DashboardActivity::class.java))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val userEmailTextView: EditText = view.findViewById(R.id.username_ed)
        val passwordTextView: EditText = view.findViewById(R.id.password_ed)

        login_btn.setOnClickListener {

            throw RuntimeException("Test Crash")
//            if (userEmailTextView.text.isNotEmpty()) {
//                callAuthentication(
//                    userEmailTextView.text.toString(),
//                    passwordTextView.text.toString()
//                )
//            } else {
//                Toast.makeText(requireActivity(), "Null value", Toast.LENGTH_SHORT).show()
//            }
        }
        forget_password_tv.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_forgetPassword)
        }
        register_tv.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }


    }


    private fun callAuthentication(email: String, pass: String) {
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d(TAG, "SignInWithEmail: Success")
                startActivity(Intent(requireActivity(), DashboardActivity::class.java))
            } else {
                Toast.makeText(requireActivity(), "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}