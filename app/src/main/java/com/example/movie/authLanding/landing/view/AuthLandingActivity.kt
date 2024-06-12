package com.example.movie.authLanding.landing.view

import LogInFragment
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.movie.R
import com.example.movie.authLanding.signUp.view.SignUpFragment
import com.example.movie.homePage.view.HomeFragment
import com.example.movie.utils.SharedPrefConstants
import com.example.movie.utils.SharedPrefsManager
import com.example.movie.utils.TextViewUtils

class AuthLandingActivity : AppCompatActivity() {
    var logInButton: Button? = null
    var txvSignUp: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_landing)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)

        SharedPrefsManager.createSharedPreferences(this)
        val isLoggedIn = SharedPrefsManager.getBoolean(SharedPrefConstants.IS_LOGGED_IN)
        if (isLoggedIn) {
            navigateToHomeFragment()
            return
        }

        // initialize views
        logInButton = findViewById<Button>(R.id.btn_login)
        txvSignUp = findViewById<TextView>(R.id.txv_signup)
        val tvWatchPlus = findViewById<TextView>(R.id.txv_watch)

        // add spannable
        TextViewUtils.setSpannable(tvWatchPlus)

        // click listeners
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
            .replace(R.id.fl_container, SignUpFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToHomeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, HomeFragment())
            .addToBackStack(HomeFragment.TAG)
            .commit()
    }
}