package com.example.firebaselogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaselogin.ui.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


    }

    override fun onBackPressed() {
        super.onBackPressed()
//        this.supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.fragments?.get(
//            0
//        ).let {
//            if (it is LoginFragment) {
//                finishAffinity()
//            } else {
//                super.onBackPressed()
//            }
//        }
    }
}