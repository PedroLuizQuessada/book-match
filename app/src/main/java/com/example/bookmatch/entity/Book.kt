package com.example.bookmatch.entity

import com.example.bookmatch.R
import com.example.bookmatch.exception.BadArgumentException

class Book(name: String, synopsis: String) {

    private val name: String
    private val synopsis: String

    fun getName(): String {
        return name
    }

    fun getSynopsis(): String {
        return synopsis
    }

    init {
        validateName(name)
        this@Book.name = name
        this@Book.synopsis = synopsis
    }

    private fun validateName(name: String) {
        if (name.isEmpty()) {
            throw BadArgumentException(R.string.change_password_button_text.toString())
        }
    }

}