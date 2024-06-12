package com.example.movie.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.movie.authLanding.landing.view.AuthLandingActivity
import com.example.movie.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue)

        val textViewMovie = findViewById<TextView>(R.id.txv_watch)

        val text = textViewMovie.text.toString()

        val spannable = SpannableStringBuilder(text)

        val eIndex = text.indexOf("+")

        if (eIndex != -1) {

            spannable.setSpan(
                ForegroundColorSpan(textViewMovie.context.getColor(R.color.blue)),
                eIndex,
                eIndex + 1,
                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textViewMovie.text = spannable

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, AuthLandingActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
