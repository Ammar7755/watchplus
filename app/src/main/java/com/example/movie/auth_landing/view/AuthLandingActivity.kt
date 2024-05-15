package com.example.movie.auth_landing.view

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R

class AuthLandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_landing_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val textViewMovie = findViewById<TextView>(R.id.txv_watch)

        val text = textViewMovie.text.toString()

        val spannable = SpannableStringBuilder(text)

        val eIndex = text.indexOf("+")

        if (eIndex != -1) {

            spannable.setSpan(
                ForegroundColorSpan(Color.BLUE),
                eIndex,
                eIndex + 1,
                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textViewMovie.text = spannable
    }
}