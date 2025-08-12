package com.example.bookmatch.entity

import android.util.Patterns

class User(email: String, password: String) {
    private var _email: String
    private var _password: String

    val email: String
        get() = _email

    val password: String
        get() = _password

    init {
        validateEmail(email)
        validatePassword(password)
        _email = email
        _password = password
    }

    private fun validateEmail(email: String) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw Exception("Invalid e-mail!")
        }
    }

    private fun validatePassword(password: String) {
        if (password.length < 6) {
            throw Exception("The password must have at least 6 characters!")
        }
    }
}