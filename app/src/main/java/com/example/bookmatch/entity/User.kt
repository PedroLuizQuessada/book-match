package com.example.bookmatch.entity

import com.example.bookmatch.exception.BadArgumentException
import com.example.bookmatch.utils.EmailUtil

class User(email: String, password: String) {
    private val email: String
    private var password: String
    private var myList: MutableList<String>
    private var reviewList: MutableList<Review>

    fun getEmail(): String {
        return email
    }

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        validatePassword(password)
        this@User.password = password
    }

    fun getMyList(): MutableList<String> {
        return myList
    }

    init {
        EmailUtil.validateEmail(email)
        validatePassword(password)
        this@User.email = email
        this@User.password = password
        myList = mutableListOf()
        reviewList = mutableListOf()
    }

    private fun validatePassword(password: String) {
        if (password.length < 6) {
            throw BadArgumentException("The password must have at least 6 characters!")
        }
    }
}