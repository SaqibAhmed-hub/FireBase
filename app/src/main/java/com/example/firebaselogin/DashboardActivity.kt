package com.example.firebaselogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        mDatabase = Firebase.database
        mAuth = Firebase.auth
        val user = mAuth.currentUser


        Logout_btn.setOnClickListener {
            if (user != null) {
                mAuth.signOut()
                startActivity(Intent(this, MainActivity::class.java))
            }

        }
    }


    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Exit")
        alertDialog.setMessage("Are you sure you want to Exit?")
        alertDialog.setPositiveButton("Yes") { _, _ ->
            finishAffinity()
        }
        alertDialog.setNegativeButton("No") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        val alert = alertDialog.create()
        alert.show()
    }

    companion object {
        private const val TAG = "DashboardActivity"
    }
}