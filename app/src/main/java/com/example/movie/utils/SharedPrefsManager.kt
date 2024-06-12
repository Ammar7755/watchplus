package com.example.movie.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPrefsManager {

    private const val SHARED_PREFERENCE_NAME = "watch_plus_prefs"

    private var sharedPreferences: SharedPreferences? = null

    fun createSharedPreferences(context: Context?) {
        sharedPreferences = context?.getSharedPreferences(
            SHARED_PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }


    fun setBoolean(key: String, value: Boolean) {
        with(sharedPreferences?.edit()) {
            this?.let {
                putBoolean(key, value)
                apply()
            }
        }
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences?.getBoolean(key, false) ?: false
    }

}