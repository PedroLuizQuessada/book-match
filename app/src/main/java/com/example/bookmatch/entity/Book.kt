package com.example.bookmatch.entity

import com.example.bookmatch.exception.BadArgumentException

class Book(name: String, author: String, synopsis: String) {

    private val name: String
    private val author: String
    private val synopsis: String

    fun getName(): String {
        return name
    }

    fun getAuthor(): String {
        return author
    }

    fun getSynopsis(): String {
        return synopsis
    }

    init {
        validateName(name)
        validateAuthor(author)
        this@Book.name = name
        this@Book.author = author
        this@Book.synopsis = synopsis
    }

    private fun validateName(name: String) {
        if (name.isEmpty()) {
            throw BadArgumentException("A book must have a name")
        }
    }

    private fun validateAuthor(author: String) {
        if (author.isEmpty()) {
            throw BadArgumentException("A book must have an author")
        }
    }

}