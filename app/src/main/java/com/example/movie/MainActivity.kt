package com.example.movie

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_landing_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val textViewMovie = findViewById<TextView>(R.id.Movie)

        val text = textViewMovie.text.toString()

        val spannable = SpannableStringBuilder(text)

        val eIndex = text.indexOf("e")

        if (eIndex != -1) {

            spannable.setSpan(
                ForegroundColorSpan(Color.WHITE),
                eIndex,
                eIndex + 1,
                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textViewMovie.text = spannable
    }
}