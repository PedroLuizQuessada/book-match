package com.example.bookmatch.entity

import com.example.bookmatch.exception.BadArgumentException
import java.util.UUID

class BookItem(bookName: String, authorName: String) {

    private val id: String
    private val bookName: String
    private val authorName: String

    fun getBookName(): String {
        return bookName
    }

    fun getAuthorName(): String {
        return authorName
    }

    init {
        validateBookName(bookName)
        validateAuthorName(authorName)
        this@BookItem.id = UUID.randomUUID().toString()
        this@BookItem.bookName = bookName
        this@BookItem.authorName = authorName
    }

    private fun validateBookName(bookName: String) {
        if (bookName.isEmpty()) {
            throw BadArgumentException("The item must have a book name!")
        }
    }

    private fun validateAuthorName(authorName: String) {
        if (authorName.isEmpty()) {
            throw BadArgumentException("The item must have an author name!")
        }
    }
}