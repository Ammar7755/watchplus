package com.example.movie.authLanding.landing.view

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R
import com.example.movie.authLanding.logIn.view.LogInFragment
import com.example.movie.authLanding.signUp.view.SignUpFragment
import com.example.movie.utils.TextViewUtils

class AuthLandingScreen : AppCompatActivity() {

    var logInButton: Button? = null
    var txvSignUp: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_landing)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // initialize views
        logInButton = findViewById<Button>(R.id.btn_login)
        txvSignUp = findViewById<TextView>(R.id.txv_signup)
        val tvWatchPlus = findViewById<TextView>(R.id.txv_watch)

        // add spannable
        TextViewUtils.setSpannable(tvWatchPlus)


        logInButton?.setOnClickListener {
            navigateToLoginFragment()
        }

        txvSignUp?.setOnClickListener {
            navigateToSignUpFragment()
        }
    }


    private fun navigateToLoginFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, LogInFragment())
            .addToBackStack(null)
            .commit()
    }
    private fun navigateToSignUpFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, SignUpFragment())
            .addToBackStack(null)
            .commit()
    }


}