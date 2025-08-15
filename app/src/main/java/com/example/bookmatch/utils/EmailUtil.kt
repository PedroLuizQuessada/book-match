package com.example.bookmatch.utils

import android.util.Patterns
import com.example.bookmatch.exception.BadArgumentException

class EmailUtil {
    companion object {
        fun validateEmail(email: String) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                throw BadArgumentException("Invalid e-mail!")
            }
        }
    }
}