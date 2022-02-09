package com.example.firebaselogin.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.firebaselogin.R
import com.example.firebaselogin.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var navController: NavController
    private var mAuth = Firebase.auth
    private lateinit var mDatabase: FirebaseDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        mDatabase = Firebase.database

        val firstnametxt: EditText = view.findViewById(R.id.firstname_ed)
        val lastnametxt: EditText = view.findViewById(R.id.lastname_ed)
        val agetxt: EditText = view.findViewById(R.id.age_ed)
        val usernametxt: EditText = view.findViewById(R.id.username_ed)
        val passwordtxt: EditText = view.findViewById(R.id.password_ed)
        val reenterpassword: EditText = view.findViewById(R.id.password_reenter_ed)


        fun validateForm(): Boolean {
            when {
                firstnametxt.text.isEmpty() -> {
                    activity?.showToast("Please enter the FirstName")
                    return false
                }
                lastnametxt.text.isEmpty() -> {
                    activity?.showToast("Please enter Lastname")
                    return false
                }
                agetxt.text.isEmpty() -> {
                    activity?.showToast("Please enter Age")
                    return false
                }
                usernametxt.text.isEmpty() -> {
                    activity?.showToast("Please enter valid Email")
                    return false
                }
                passwordtxt.text.isEmpty() -> {
                    activity?.showToast("Please Enter the Password")
                    return false
                }
                reenterpassword.text.isEmpty() -> {
                    activity?.showToast("Please reenter the Password")
                    return false
                }
                passwordtxt.text.toString() != reenterpassword.text.toString() -> {
                    activity?.showToast("Please type the correct password")
                    return false
                }
                else -> return true
            }
        }


        register_btn.setOnClickListener {
            if (validateForm()) {
                callRegister(usernametxt.text.toString(), passwordtxt.text.toString())
                saveToDatabase()
            }
        }

    }

    private fun saveToDatabase() {
        val ref = mDatabase.reference
        val user = User(
            firstname = firstname_ed.text.toString(),
            lastname = lastname_ed.text.toString(),
            age = age_ed.text.toString().toInt(),
            username = username_ed.text.toString()
        )
        ref.child("Users")
            .child(firstname_ed.text.toString()).setValue(user)
            .addOnCompleteListener {

        }
    }

    private fun callRegister(email: String, pass: String) {
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(requireActivity(), "Register Successfully", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(requireActivity(), "Unable to Register", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun Context.showToast(msg: String) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "RegisterFragment"
    }

}