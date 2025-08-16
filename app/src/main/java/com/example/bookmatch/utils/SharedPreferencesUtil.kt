package com.example.bookmatch.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.content.edit

class SharedPreferencesUtil {
    companion object {
        fun addKey(context: Context, name: String, key: String, value: String) {
            val preferences = context.getSharedPreferences(name, MODE_PRIVATE)
            preferences.edit {
                putString(key, value)
            }
        }

        fun removeKey(context: Context, name: String, key: String) {
            val preferences = context.getSharedPreferences(name, MODE_PRIVATE)
            preferences.edit {
                remove(key)
            }
        }
    }
}