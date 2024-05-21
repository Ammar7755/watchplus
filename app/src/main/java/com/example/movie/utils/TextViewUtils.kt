package com.example.movie.utils

import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.example.movie.R

object TextViewUtils {

    fun setSpannable(textView: TextView?) {
        textView?.let { textView ->
            val text = textView.text.toString()
            val spannable = SpannableStringBuilder(text)

            val eIndex = text.indexOf("+")

            if (eIndex != -1) {
                spannable.setSpan(
                    ForegroundColorSpan(textView.context.getColor(R.color.blue)),
                    eIndex,
                    eIndex + 1,
                    SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            textView.text = spannable
        }
    }

}