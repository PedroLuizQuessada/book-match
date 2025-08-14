package com.example.bookmatch.entity

import com.example.bookmatch.exception.BadArgumentException

class Review(bookName: String, review: String, rating: Int?) {

    private var _bookName: String
    private var _review: String
    private var _rating: Int?

    val bookName: String
        get() = _bookName

    val review: String
        get() = _review

    val rating: Int?
        get() = _rating

    init {
        validateBookName(bookName)
        _bookName = bookName
        _review = review
        _rating = rating
    }

    private fun validateBookName(bookName: String) {
        if (bookName.isEmpty()) {
            throw BadArgumentException("The review must have a book name!")
        }
    }
}