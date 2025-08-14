package com.example.bookmatch.entity

import android.util.Patterns
import com.example.bookmatch.exception.BadArgumentException

class User(email: String, password: String) {
    private var _email: String
    private var _password: String

    val email: String
        get() = _email

    var password: String = ""
        set(password) {
            validatePassword(password)
            field = password
        }

    init {
        validateEmail(email)
        validatePassword(password)
        _email = email
        _password = password
    }

    private fun validateEmail(email: String) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw BadArgumentException("Invalid e-mail!")
        }
    }

    private fun validatePassword(password: String) {
        if (password.length < 6) {
            throw BadArgumentException("The password must have at least 6 characters!")
        }
    }
}