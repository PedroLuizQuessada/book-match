package com.example.bookmatch.entity

import com.example.bookmatch.exception.BadArgumentException

class Book(name: String, synopsis: String) {

    private var _name: String
    private var _synopsis: String

    val name: String
        get() = _name

    val synopsis: String
        get() = _synopsis

    init {
        validateName(name)
        _name = name
        _synopsis = synopsis
    }

    private fun validateName(name: String) {
        if (name.isEmpty()) {
            throw BadArgumentException("The book must have a name!")
        }
    }

}