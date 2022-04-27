package com.example.firebaselogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
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

        /*
        * This will generate the firebase registration Token
        * */
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful){
                Log.d(TAG, "Firebase Token: ${task.exception}")
            }
            val token = task.result
            Log.i(TAG, "Firebase Token: $token ")
        }

        /*
        * Subscribe Button
        * */
        Subscribe_btn.setOnClickListener {
            Firebase.messaging.subscribeToTopic("weather")
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful){
                        Log.i(TAG, "Firebase Subscribe: ${task.exception}")
                    }
                    Toast.makeText(this,"You are Subscribe to our channel",Toast.LENGTH_SHORT).show()
                }
        }

        /*
        * UnSubSubscribe Button
        * */
        Unsubscribe_btn.setOnClickListener {
            Firebase.messaging.unsubscribeFromTopic("weather")
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful){
                        Log.i(TAG, "Firebase Subscribe: ${task.exception}")
                    }
                    Toast.makeText(this,"You are UnSubscribe to our channel",Toast.LENGTH_SHORT).show()
                }
        }

        /*
        * Logout Button
        * */
        logout_btn.setOnClickListener {
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